package by.ourgame.bot.model;

import by.ourgame.bot.api.dto.Chat;
import by.ourgame.bot.api.dto.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {
    private String id;
    private User creator;
    private Chat chat;
    private Integer creatorMessageId;
    private Integer chatMessageId;
    private boolean canAnswer;
}
