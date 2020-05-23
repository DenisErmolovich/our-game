package by.ourgame.bot.service;

import by.ourgame.bot.api.dto.Chat;
import by.ourgame.bot.api.dto.Message;
import by.ourgame.bot.api.dto.Update;
import by.ourgame.bot.api.dto.User;
import by.ourgame.bot.api.dto.request.SendMessageRequest;
import by.ourgame.bot.api.method.SendMessageMethod;
import by.ourgame.bot.button.InlineMurkUp;
import by.ourgame.bot.model.Game;
import by.ourgame.bot.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CommandService {
    private SendMessageMethod sendMessageMethod;
    private GameRepository gameRepository;
    private ChatService chatService;

    public CommandService(SendMessageMethod sendMessageMethod,
                          GameRepository gameRepository,
                          ChatService chatService) {
        this.sendMessageMethod = sendMessageMethod;
        this.gameRepository = gameRepository;
        this.chatService = chatService;
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
                        "Набирай команду /startGame и я пришлю в этот чат кнопку для ответов, " +
                        "а тебе лично кнопки управления и ты станешь ведущим.\n" +
                        "Кнопка 'Ответить' появляется у игроков в чате после того как " +
                        "ведущий нажмёт 'Разрешить отвечать' или отвечающий даст не правильный ответ " +
                        "(у ведущего кнопка 'нет').\n" +
                        "Будьте внимательным и не упустите свой шанс!\n" +
                        "\n" +
                        "Некоторые особенности:\n" +
                        "* Один человек может создать только одну игру\n" +
                        "* В одном чате может быть создана только одна игра\n" +
                        "* Бот должен быть админом чата\n" +
                        "* На время игры все сообщения в чате блокируются\n" +
                        "* Нажатие кноки 'Ответить' обрабатываются в том порядке, в котором они пришли на " +
                        "сервера Telegram\n" +
                        "\n" +
                        "Смотри что я могу:\n" +
                        "/startGame - начать новую игру\n" +
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
        gameRepository.findByChat_IdOrCreator_Id(chat.getId(), user.getId())
                .doOnNext(this::logThatGameHasBeenFound)
                .switchIfEmpty(createGame(update))
                .flatMap(chatService::switchOffMessagesInChat)
                .subscribe();
    }

    private Mono<Game> createGame(Update update) {
        var user = update.getMessage().getFrom();
        var chat = update.getMessage().getChat();
        return gameRepository.save(Game.builder().creator(user).chat(chat).canAnswer(false).build())
                .doOnNext(this::logThatGameHasBeenSaved)
                .zipWith(sendChatMessage(chat),
                        (game, message) -> game.withChatMessageId(message.getMessageId()))
                .zipWith(sendCreatorMessage(user),
                        (game, message) -> game.withCreatorMessageId(message.getMessageId()))
                .flatMap(game -> gameRepository.save(game))
                .doOnNext(this::logThatGameHasBeenUpdate);
    }

    private Mono<Message> sendMessage(String text, Update update) {
        var sendMessageRq = SendMessageRequest
                .builder()
                .chatId(update.getMessage().getChat().getId())
                .text(text)
                .build();
        return sendMessageMethod.perform(sendMessageRq)
                .doOnNext(message -> log.info("Message has been sent with text:\n{}", message.getText()));
    }

    private void sendMessageWithSubscription(String text, Update update) {
        sendMessage(text, update).subscribe();
    }

    private Mono<Message> sendChatMessage(Chat chat) {
        return sendMessageMethod
                .perform(SendMessageRequest.builder()
                        .chatId(chat.getId())
                        .text("Кнопка 'Ответить' появляется у игроков в чате после того как " +
                                "ведущий нажмёт 'Разрешить отвечать' или отвечающий даст не правильный ответ.\n" +
                                "Будьте внимательны и не упустите свой шанс!")
                        .build())
                .doOnNext(this::logChatMessageHasBeenSent);
    }

    private Mono<Message> sendCreatorMessage(User user) {
        return sendMessageMethod
                .perform(SendMessageRequest.builder()
                        .chatId(user.getId())
                        .text("Управляй игрой!")
                        .replyMarkup(InlineMurkUp.ALLOW.getReplyMarkup())
                        .build())
                .doOnNext(this::logCreatorMessageHasBeenSent);
    }

    private void logThatGameHasBeenFound(Game game) {
        log.info("Game has been found: [id: {}, chat: {}, creator: {} {}]",
                game.getId(),
                game.getChat().getTitle(),
                game.getCreator().getFirstName(),
                game.getCreator().getLastName());
    }

    private void logThatGameHasBeenSaved(Game game) {
        log.info("Game has been saved: [id: {}, chat: {}, creator: {} {}]",
                game.getId(),
                game.getChat().getTitle(),
                game.getCreator().getFirstName(),
                game.getCreator().getLastName());
    }

    private void logThatGameHasBeenUpdate(Game game) {
        log.info("Game has been updated: [id: {}, creatorMessageId: {}, chatMessageId: {}]",
                game.getId(),
                game.getCreatorMessageId(),
                game.getChatMessageId());
    }

    private void logChatMessageHasBeenSent(Message message) {
        log.info("Chat message for answers has been sent: [chatMessageId: {}]", message.getMessageId());
    }

    private void logCreatorMessageHasBeenSent(Message message) {
        log.info("Creator message for control has been sent: [creatorMessageId: {}]", message.getMessageId());
    }
}
