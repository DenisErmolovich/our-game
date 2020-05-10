package by.ourgame.bot.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseParameters {
    @JsonProperty("migrate_to_chat_id")
    private Long migrateToChatId;

    @JsonProperty("retry_after")
    private Long retryAfter;
}
