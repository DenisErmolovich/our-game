package by.ourgame.bot.service;

import by.ourgame.bot.api.dto.Message;
import by.ourgame.bot.api.dto.Update;
import by.ourgame.bot.api.dto.request.SendMessageRequest;
import by.ourgame.bot.api.method.SendMessageMethod;
import by.ourgame.bot.button.InlineMurkUp;
import by.ourgame.bot.model.Game;
import by.ourgame.bot.repository.GameRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CommandService {
    private SendMessageMethod sendMessageMethod;
    private GameRepository gameRepository;

    public CommandService(SendMessageMethod sendMessageMethod,
                          GameRepository gameRepository) {
        this.sendMessageMethod = sendMessageMethod;
        this.gameRepository = gameRepository;
    }

    public void processStartCommand(Update update) {
        sendMessageWithSubscription("" +
                        "Привет, я помогу определить кто первый будет отвечать на вопрос =)\n" +
                        "Если у самого есть вопросы, то не стесняйся нажать /help" +
                        "",
                update);
    }

    public void processHelpCommand(Update update) {
        sendMessageWithSubscription("" +
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

    public void processSettingsCommand(Update update) {
        sendMessageWithSubscription("Мне не нужны настройки," +
                "но если есть интересные идеи, то пиши @DenisErmolovich", update);
    }

    public void processNotSupportedCommand(Update update) {
        sendMessageWithSubscription("Я не знаю такой команды =(", update);
    }

    public void processStartGame(Update update) {
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
                        (game, message) -> game.withChatMessageId(message.getMessageId())
                )
                .zipWith(sendMessageMethod.perform(SendMessageRequest.builder()
                                .chatId(user.getId())
                                .text("Управляй игрой!")
                                .replyMarkup(InlineMurkUp.ALLOW.getReplyMarkup())
                                .build()),
                        (game, message) -> game.withCreatorMessageId(message.getMessageId())
                )
                .flatMap(game -> gameRepository.save(game))
                .subscribe();
    }

    public void processFinishGame(Update update) {
        var chat = update.getMessage().getChat();
        gameRepository
                .findByChat_Id(chat.getId())
                .flatMap(game -> gameRepository.delete(game))
                .then(sendMessage("Игра закончена =(", update))
                .subscribe();
    }

    private Mono<Message> sendMessage(String text, Update update) {
        var sendMessageRq = SendMessageRequest
                .builder()
                .chatId(update.getMessage().getChat().getId())
                .text(text)
                .build();
        return sendMessageMethod.perform(sendMessageRq);
    }

    private void sendMessageWithSubscription(String text, Update update) {
        sendMessage(text, update).subscribe();
    }
}
