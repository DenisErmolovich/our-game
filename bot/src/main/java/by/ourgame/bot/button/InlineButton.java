package by.ourgame.bot.button;

import by.ourgame.bot.api.dto.request.InlineKeyboardButton;

public enum InlineButton {
    ANSWER(InlineKeyboardButton.builder().text("Ответить").callbackData("answer").build()),
    ALLOW(InlineKeyboardButton.builder().text("Разрешить отвечать").callbackData("allow").build()),
    YES(InlineKeyboardButton.builder().text("Да").callbackData("yes").build()),
    NO(InlineKeyboardButton.builder().text("Нет").callbackData("no").build()),
    WAIT(InlineKeyboardButton.builder().text("Кнопка антистресс").callbackData("wait").build());

    private InlineKeyboardButton button;

    InlineButton(InlineKeyboardButton button) {
        this.button = button;
    }

    public InlineKeyboardButton getButton() {
        return button;
    }
}
