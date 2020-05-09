package by.ourgame.bot.button;

import by.ourgame.bot.api.dto.request.ReplyMarkup;

import java.util.List;

public enum InlineMurkUp {
    ANSWER(ReplyMarkup.builder().inlineKeyboard(List.of(List.of(InlineButton.ANSWER.getButton()))).build()),
    ALLOW(ReplyMarkup.builder().inlineKeyboard(List.of(List.of(InlineButton.ALLOW.getButton()))).build()),
    IS_ANSWER_RIGHT(ReplyMarkup.builder().inlineKeyboard(List.of(List.of(
            InlineButton.YES.getButton(),
            InlineButton.NO.getButton()))).build()),
    WAIT(ReplyMarkup.builder().inlineKeyboard(List.of(List.of(InlineButton.WAIT .getButton()))).build());

    private ReplyMarkup replyMarkup;

    InlineMurkUp(ReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
    }

    public ReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }
}
