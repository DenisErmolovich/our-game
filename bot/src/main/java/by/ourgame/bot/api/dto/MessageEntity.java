package by.ourgame.bot.api.dto;

import lombok.Data;

@Data
public class MessageEntity {
    private String type;
    private Integer offset;
    private Integer length;
    private String url;
    private User user;
    private String language;
}
