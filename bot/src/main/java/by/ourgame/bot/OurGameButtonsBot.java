package by.ourgame.bot;

import by.ourgame.bot.api.TelegramReactiveBot;
import by.ourgame.bot.api.config.BotConfig;
import by.ourgame.bot.api.dto.Message;
import by.ourgame.bot.api.dto.Update;
import by.ourgame.bot.api.dto.request.EditMessageTextRequest;
import by.ourgame.bot.api.dto.request.SendMessageRequest;
import by.ourgame.bot.api.method.EditMessageTextMethod;
import by.ourgame.bot.api.method.GetUpdatesMethod;
import by.ourgame.bot.api.method.SendMessageMethod;
import by.ourgame.bot.button.InlineMurkUp;
import by.ourgame.bot.model.Game;
import by.ourgame.bot.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Component
@Slf4j
public class OurGameButtonsBot extends TelegramReactiveBot {
    private SendMessageMethod sendMessageMethod;
    private GameRepository gameRepository;
    private EditMessageTextMethod editMessageTextMethod;

    public OurGameButtonsBot(BotConfig config,
                             GetUpdatesMethod getUpdatesMethod,
                             SendMessageMethod sendMessageMethod,
                             GameRepository gameRepository,
                             EditMessageTextMethod editMessageTextMethod) {
        super(config, getUpdatesMethod);
        this.sendMessageMethod = sendMessageMethod;
        this.gameRepository = gameRepository;
        this.editMessageTextMethod = editMessageTextMethod;
    }

    @Override
    public Consumer<Update> getStartProcessor() {
        return update -> sendMessageWithSubscription("" +
                "Привет, я помогу определить кто первый будет отвечать на вопрос =)\n" +
                "Если у самого есть вопросы, то не стесняйся нажать /help" +
                "",
                update);
    }

    @Override
    public Consumer<Update> getHelpProcessor() {
        return update -> sendMessageWithSubscription("" +
                        "Набирай команду /startGame и я пришлю в этот чат кнопку для ответов," +
                        "а тебе лично кнопки управления.\n" +
                        "Кнопка 'Ответить' не будет ничего делать, пока ведущий не нажмёт 'Разрешить отвечать," +
                        "после чего первый нажавший получит шанс ответить на вопрос.'\n" +
                        "После правильного ответа все начинается с начала (у ведущего кнопка 'да')," +
                        "а в случае не правильного ответа (у ведущего кнопка 'нет') продолжится схватка за" +
                        "право ответить.\n" +
                        "Кнопка 'анитистесс' просто ничего не делает =)\n" +
                        "Не забудь завершить игру /finishGame, чтобы дать шанс другому начать её!!!\n" +
                        "\n" +
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
        commands.put("/startgame", this::processStartGame);
        commands.put("/finishgame", this::processFinishGame);
        return commands;
    }

    @Override
    public void processUpdateIfNotCommand(Update update) {
    }

    @Override
    public void processInlineCommand(Update update) {
        var query = update.getCallbackQuery().getData();
        Optional.ofNullable(getInlineQueryProcessorMap().get(query))
                .ifPresent(processor -> processor.accept(update));
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
                .switchIfEmpty(gameRepository.save(Game.builder()
                        .creator(user)
                        .chat(chat)
                        .canAnswer(false)
                        .build()))
                .zipWith(sendMessageMethod.perform(SendMessageRequest.builder()
                                .chatId(chat.getId())
                                .text("Нажимай кнопку, если готов ответить!")
                                .replyMarkup(InlineMurkUp.ANSWER.getReplyMarkup())
                                .build()),
                        (game, message) -> {
                            var gameWithType = ((Game) game);
                            gameWithType.setChatMessageId(message.getMessageId());
                            return gameWithType;
                        }
                )
                .zipWith(sendMessageMethod.perform(SendMessageRequest.builder()
                                .chatId(user.getId())
                                .text("Управляй игрой!")
                                .replyMarkup(InlineMurkUp.ALLOW.getReplyMarkup())
                                .build()),
                        (game, message) -> {
                            game.setCreatorMessageId(message.getMessageId());
                            return game;
                         }
                )
                .flatMap(game -> gameRepository.save(game))
                .subscribe();
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

    public Map<String, Consumer<Update>> getInlineQueryProcessorMap() {
        var queryProcessors = new HashMap<String, Consumer<Update>>();
        queryProcessors.put("answer", this::processAnswerQuery);
        queryProcessors.put("allow", this::processAllowQuery);
        queryProcessors.put("yes", this::processYesQuery);
        queryProcessors.put("no", this::processNoQuery);
        queryProcessors.put("wait", this::processWaitQuery);
        return queryProcessors;
    }

    private void processAnswerQuery(Update update) {
        var user = update.getCallbackQuery().getFrom();
        var message = update.getCallbackQuery().getMessage();
        var chat = message.getChat();
        var textTemplate = "Отвечает %s %s. Ответ правильный?";
        var text = String.format(textTemplate, user.getFirstName(), user.getLastName());
        gameRepository.findByChat_IdAndCanAnswer(chat.getId(), true)
                .flatMap(game -> {
                    game.setCanAnswer(false);
                    return gameRepository.save(game);
                })
                .flatMap(game -> editMessageTextMethod.perform(
                        EditMessageTextRequest.builder()
                                .chatId(game.getCreator().getId().toString())
                                .messageId(game.getCreatorMessageId())
                                .text(text)
                                .replyMarkup(InlineMurkUp.IS_ANSWER_RIGHT.getReplyMarkup())
                                .build()))
                .subscribe();
    }

    private void processAllowQuery(Update update) {
        var message = update.getCallbackQuery().getMessage();
        var chat = message.getChat();
        var user = update.getCallbackQuery().getFrom();
        gameRepository.findByCreator_Id(user.getId())
                .flatMap(game -> {
                    game.setCanAnswer(true);
                    return gameRepository.save(game);
                })
                .then(editMessageTextMethod.perform(
                        EditMessageTextRequest.builder()
                                .chatId(chat.getId().toString())
                                .messageId(message.getMessageId())
                                .text("Ждём ответа...")
                                .replyMarkup(InlineMurkUp.WAIT.getReplyMarkup())
                                .build()))
                .subscribe();
    }

    private void processYesQuery(Update update) {
        var message = update.getCallbackQuery().getMessage();
        var chat = message.getChat();
        var user = update.getCallbackQuery().getFrom();
        gameRepository.findByCreator_Id(user.getId())
                .flatMap(game -> {
                    game.setCanAnswer(false);
                    return gameRepository.save(game);
                })
                .then(editMessageTextMethod.perform(
                        EditMessageTextRequest.builder()
                                .chatId(chat.getId().toString())
                                .messageId(message.getMessageId())
                                .text("Управляй игрой!")
                                .replyMarkup(InlineMurkUp.ALLOW.getReplyMarkup())
                                .build()))
                .subscribe();
    }

    private void processNoQuery(Update update) {
        var message = update.getCallbackQuery().getMessage();
        var chat = message.getChat();
        var user = update.getCallbackQuery().getFrom();
        gameRepository.findByCreator_Id(user.getId())
                .flatMap(game -> {
                    game.setCanAnswer(true);
                    return gameRepository.save(game);
                })
                .then(editMessageTextMethod.perform(
                        EditMessageTextRequest.builder()
                                .chatId(chat.getId().toString())
                                .messageId(message.getMessageId())
                                .text("Ждём ответа...")
                                .replyMarkup(InlineMurkUp.WAIT.getReplyMarkup())
                                .build()))
                .subscribe();
    }

    private void processWaitQuery(Update update) {
    }
}
