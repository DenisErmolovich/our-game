package by.ourgame.bot.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// ToDo: add skipped fields
@Data
public class Update {
    @JsonProperty("update_id")
    private Integer updateId;

    private Message message;

    @JsonProperty("edited_message")
    private Message editedMessage;

    @JsonProperty("channel_post")
    private Message channelPost;

    @JsonProperty("edited_channel_post")
    private Message editedChannelPost;

    @JsonProperty("callback_query")
    private CallbackQuery callbackQuery;
}
