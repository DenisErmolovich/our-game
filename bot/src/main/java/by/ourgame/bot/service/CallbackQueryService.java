package by.ourgame.bot.service;

import by.ourgame.bot.api.dto.Update;
import by.ourgame.bot.api.dto.request.AnswerCallbackQueryRequest;
import by.ourgame.bot.api.dto.request.DeleteMessageRequest;
import by.ourgame.bot.api.dto.request.EditMessageTextRequest;
import by.ourgame.bot.api.method.AnswerCallbackQueryMethod;
import by.ourgame.bot.api.method.DeleteMessageMethod;
import by.ourgame.bot.api.method.EditMessageTextMethod;
import by.ourgame.bot.button.InlineMurkUp;
import by.ourgame.bot.model.Game;
import by.ourgame.bot.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CallbackQueryService {
    private GameRepository gameRepository;
    private EditMessageTextMethod editMessageTextMethod;
    private DeleteMessageMethod deleteMessageMethod;
    private ChatService chatService;
    private AnswerCallbackQueryMethod answerCallbackQueryMethod;

    public CallbackQueryService(GameRepository gameRepository,
                                EditMessageTextMethod editMessageTextMethod,
                                DeleteMessageMethod deleteMessageMethod,
                                ChatService chatService,
                                AnswerCallbackQueryMethod answerCallbackQueryMethod) {
        this.gameRepository = gameRepository;
        this.editMessageTextMethod = editMessageTextMethod;
        this.deleteMessageMethod = deleteMessageMethod;
        this.chatService = chatService;
        this.answerCallbackQueryMethod = answerCallbackQueryMethod;
    }

    public void processAnswerQuery(Update update) {
        var user = update.getCallbackQuery().getFrom();
        var message = update.getCallbackQuery().getMessage();
        var chat = message.getChat();
        var textTemplate = "Отвечает %s %s. Ответ правильный?";
        var text = String.format(textTemplate, user.getFirstName(), user.getLastName());
        gameRepository.findByChat_IdAndCanAnswer(chat.getId(), true)
                .doOnNext(this::logThatGameHasBeenFound)
                .flatMap(game -> gameRepository.save(game.withCanAnswer(false)))
                .doOnNext(this::logThatGameHasBeenUpdate)
                .flatMap(game -> editMessageTextMethod.perform(
                        EditMessageTextRequest.builder()
                                .chatId(game.getCreator().getId().toString())
                                .messageId(game.getCreatorMessageId())
                                .text(text)
                                .replyMarkup(InlineMurkUp.IS_ANSWER_RIGHT.getReplyMarkup())
                                .build()))
                .doOnNext(messageWithMarkup -> log.info("{} {} will try to answer",
                        user.getFirstName(), user.getLastName()))
                .doFirst(() -> log.info("{} {} pressed answer button",
                        user.getFirstName(), user.getLastName()))
                .flatMap(messageWithMarkup -> answerCallbackQuery(update.getCallbackQuery().getId(), "Ответ принят"))
                .switchIfEmpty(answerCallbackQuery(update.getCallbackQuery().getId(), "Вы не успели =("))
                .subscribe();
    }

    public void processAllowQuery(Update update) {
        allowToAnswer(update);
    }

    public void processYesQuery(Update update) {
        var message = update.getCallbackQuery().getMessage();
        var chat = message.getChat();
        editMessageTextMethod.perform(
                EditMessageTextRequest.builder()
                        .chatId(chat.getId().toString())
                        .messageId(message.getMessageId())
                        .text("Управляй игрой!")
                        .replyMarkup( InlineMurkUp.ALLOW.getReplyMarkup())
                        .build())
                .doOnNext(messageWithMarkup -> log.info("Right answer =)"))
                .then(answerCallbackQuery(update.getCallbackQuery().getId(), "Правильный ответ"))
                .subscribe();
    }

    public void processNoQuery(Update update) {
        allowToAnswer(update);
    }

    public void processResetQuery(Update update) {
        var user = update.getCallbackQuery().getFrom();
        var message = update.getCallbackQuery().getMessage();
        var chat = message.getChat();
        gameRepository.findByCreator_Id(user.getId())
                .doOnNext(this::logThatGameHasBeenFound)
                .flatMap(game -> gameRepository.save(game.withCanAnswer(false)))
                .doOnNext(this::logThatGameHasBeenUpdate)
                .flatMap(game -> editMessageTextMethod.perform(
                        EditMessageTextRequest.builder()
                                .chatId(chat.getId().toString())
                                .messageId(message.getMessageId())
                                .text("Управляй игрой!")
                                .replyMarkup( InlineMurkUp.ALLOW.getReplyMarkup())
                                .build()))
                .doFirst(() -> log.info("Reset answer"))
                .then(answerCallbackQuery(update.getCallbackQuery().getId(), "Сброс выполнен"))
                .subscribe();
    }

    public void processFinishQuery(Update update) {
        var user = update.getCallbackQuery().getFrom();
        gameRepository.findByCreator_Id(user.getId())
                .doOnNext(this::logThatGameHasBeenFound)
                .flatMap(this::deleteGameMessages)
                .flatMap(chatService::switchOnMessagesInChat)
                .flatMap(game -> gameRepository.delete(game))
                .doOnSuccess(aVoid -> log.info("Game has been deleted"))
                .then(answerCallbackQuery(update.getCallbackQuery().getId(), "Игра завершена"))
                .subscribe();
    }

    private void allowToAnswer(Update update) {
        var message = update.getCallbackQuery().getMessage();
        var chat = message.getChat();
        var user = update.getCallbackQuery().getFrom();
        gameRepository.findByCreator_Id(user.getId())
                .doOnNext(this::logThatGameHasBeenFound)
                .flatMap(game -> gameRepository.save(game.withCanAnswer(true)))
                .doOnNext(this::logThatGameHasBeenUpdate)
                .then(editMessageTextMethod.perform(
                        EditMessageTextRequest.builder()
                                .chatId(chat.getId().toString())
                                .messageId(message.getMessageId())
                                .text("Ждём ответа...")
                                .replyMarkup(InlineMurkUp.RESET.getReplyMarkup())
                                .build()))
                .doOnNext(messageWithMarkup -> log.info("Waiting for answer"))
                .then(answerCallbackQuery(update.getCallbackQuery().getId(), "Ждём ответа от игроков"))
                .subscribe();
    }

    public void processWaitQuery(Update update) {
    }

    private void logThatGameHasBeenFound(Game game) {
        log.info("Game has been found: [id: {}, chat: {}, creator: {} {}]",
                game.getId(),
                game.getChat().getTitle(),
                game.getCreator().getFirstName(),
                game.getCreator().getLastName());
    }

    private void logThatGameHasBeenUpdate(Game game) {
        log.info("Game has been updated: [id: {}, isCanAnswer: {}]",
                game.getId(),
                game.isCanAnswer());
    }

    private Mono<Game> deleteGameMessages(Game game) {
        var deleteChatMessage = deleteMessageMethod
                .perform(DeleteMessageRequest.builder()
                        .chatId(game.getChat().getId().toString())
                        .messageId(game.getChatMessageId())
                        .build())
                .doOnSuccess(aBoolean -> log.info("Chat message has been deleted."));
        var deleteCreatorMessage = deleteMessageMethod
                .perform(DeleteMessageRequest.builder()
                        .chatId(game.getCreator().getId().toString())
                        .messageId(game.getCreatorMessageId())
                        .build())
                .doOnSuccess(aBoolean -> log.info("Creator message has been deleted."));
        return Mono.zip(deleteChatMessage, deleteCreatorMessage, (result1, result) -> game);
    }

    private Mono<Boolean> answerCallbackQuery(String callbackQueryId, String text) {
        return answerCallbackQueryMethod.perform(
                AnswerCallbackQueryRequest.builder()
                        .callbackQueryId(callbackQueryId)
                        .text(text)
                        .build()
        );
    }
}
