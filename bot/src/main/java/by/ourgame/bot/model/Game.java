package by.ourgame.bot.model;

import by.ourgame.bot.api.dto.Chat;
import by.ourgame.bot.api.dto.User;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@Builder
public class Game {
    private String id;

    private User creator;

    private Chat chat;

    @With
    private Long creatorMessageId;

    @With
    private Long chatMessageId;

    @With
    private boolean canAnswer;
}
