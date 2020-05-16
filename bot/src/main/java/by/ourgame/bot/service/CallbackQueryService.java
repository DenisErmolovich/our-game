package by.ourgame.bot.service;

import by.ourgame.bot.api.dto.Update;
import by.ourgame.bot.api.dto.request.EditMessageTextRequest;
import by.ourgame.bot.api.method.EditMessageTextMethod;
import by.ourgame.bot.button.InlineMurkUp;
import by.ourgame.bot.model.Game;
import by.ourgame.bot.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CallbackQueryService {
    private GameRepository gameRepository;
    private EditMessageTextMethod editMessageTextMethod;

    public CallbackQueryService(GameRepository gameRepository, EditMessageTextMethod editMessageTextMethod) {
        this.gameRepository = gameRepository;
        this.editMessageTextMethod = editMessageTextMethod;
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
}
