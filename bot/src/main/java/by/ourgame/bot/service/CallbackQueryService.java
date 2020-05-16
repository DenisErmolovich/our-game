package by.ourgame.bot.service;

import by.ourgame.bot.api.dto.Update;
import by.ourgame.bot.api.dto.request.EditMessageTextRequest;
import by.ourgame.bot.api.method.EditMessageTextMethod;
import by.ourgame.bot.button.InlineMurkUp;
import by.ourgame.bot.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
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
                .flatMap(game -> gameRepository.save(game.withCanAnswer(false)))
                .flatMap(game -> editMessageTextMethod.perform(
                        EditMessageTextRequest.builder()
                                .chatId(game.getCreator().getId().toString())
                                .messageId(game.getCreatorMessageId())
                                .text(text)
                                .replyMarkup(InlineMurkUp.IS_ANSWER_RIGHT.getReplyMarkup())
                                .build()))
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
                .subscribe();
    }

    public void processNoQuery(Update update) {
        allowToAnswer(update);
    }

    private void allowToAnswer(Update update) {
        var message = update.getCallbackQuery().getMessage();
        var chat = message.getChat();
        var user = update.getCallbackQuery().getFrom();
        gameRepository.findByCreator_Id(user.getId())
                .flatMap(game -> gameRepository.save(game.withCanAnswer(true)))
                .then(editMessageTextMethod.perform(
                        EditMessageTextRequest.builder()
                                .chatId(chat.getId().toString())
                                .messageId(message.getMessageId())
                                .text("Ждём ответа...")
                                .replyMarkup(InlineMurkUp.WAIT.getReplyMarkup())
                                .build()))
                .subscribe();
    }

    public void processWaitQuery(Update update) {
    }
}
