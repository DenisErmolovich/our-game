package by.ourgame.bot;

import by.ourgame.bot.api.TelegramReactiveBot;
import by.ourgame.bot.api.config.BotConfig;
import by.ourgame.bot.api.dto.Message;
import by.ourgame.bot.api.dto.Update;
import by.ourgame.bot.api.dto.request.SendMessageRequest;
import by.ourgame.bot.api.method.GetUpdatesMethod;
import by.ourgame.bot.api.method.SendMessageMethod;
import by.ourgame.bot.model.Game;
import by.ourgame.bot.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
@Slf4j
public class OurGameButtonsBot extends TelegramReactiveBot {
    private SendMessageMethod sendMessageMethod;
    private GameRepository gameRepository;

    public OurGameButtonsBot(BotConfig config,
                             GetUpdatesMethod getUpdatesMethod,
                             SendMessageMethod sendMessageMethod,
                             GameRepository gameRepository) {
        super(config, getUpdatesMethod);
        this.sendMessageMethod = sendMessageMethod;
        this.gameRepository = gameRepository;
    }

    @Override
    public Consumer<Update> getStartProcessor() {
        return update -> sendMessageWithSubscription("" +
                "Привет, я помогу определить кто первый будет отвечать на вопрос =)\n" +
                "",
                update);
    }

    @Override
    public Consumer<Update> getHelpProcessor() {
        return update -> sendMessageWithSubscription("" +
                        "Смотри что я могу:\n" +
                        "/startGame - начать новую игру\n" +
                        "/finishGame - закончить текущую игру\n" +
                        "/start - запустиь бота\n" +
                        "/help - расскажу что умею\n" +
                        "/settings - настройки\n" +
                        "",
                update);
    }

    @Override
    public Consumer<Update> getSettingsProcessor() {
        return update -> sendMessageWithSubscription("Мне не нужны настройки," +
                "но если есть интересные идеи, то пиши @DenisErmolovich", update);
    }

    @Override
    public Consumer<Update> getNotSupportedCommandProcessor() {
        return update -> sendMessageWithSubscription("Я не знаю такой команды =(", update);

    }

    @Override
    public Map<String, Consumer<Update>> getCustomCommandProcessorMap() {
        var commands = new HashMap<String, Consumer<Update>>();
        commands.put("/startGame", this::processStartGame);
        commands.put("/finishGame", this::processFinishGame);
        return commands;
    }

    @Override
    public void processUpdateIfNotCommand(Update update) {
        sendMessageWithSubscription("" +
                        "Я ещё не на столько умный, чтобы понимать тебя =(.\n" +
                        "Используй комманды начинающиеся с / или нажми на /help" +
                        "",
                update);
    }

    public Mono<Message> sendMessage(String text, Update update) {
        var sendMessageRq = SendMessageRequest
                .builder()
                .chatId(update.getMessage().getChat().getId())
                .text(text)
                .build();
        return sendMessageMethod.perform(sendMessageRq);
    }

    private void processStartGame(Update update) {
        var user = update.getMessage().getFrom();
        var chat = update.getMessage().getChat();
        gameRepository
                .findByChat_Id(chat.getId())
                .doOnSuccess(gameFromDb -> sendMessage("Игра уже создана =(", update))
                .switchIfEmpty(gameRepository.save(Game.builder().creator(user).chat(chat).build()))
                .subscribe(game -> sendMessage("Игра создана =)", update));
    }

    private void processFinishGame(Update update) {
        var chat = update.getMessage().getChat();
        gameRepository
                .findByChat_Id(chat.getId())
                .flatMap(game -> gameRepository.delete(game))
                .then(sendMessage("Игра закончена =(", update))
                .subscribe();
    }

    private void sendMessageWithSubscription(String text, Update update) {
        sendMessage(text, update).subscribe();
    }
}
