package by.ourgame.bot.api.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class AnswerCallbackQueryRequest {
    @JsonProperty("callback_query_id")
    private String callbackQueryId;

    private String text;

    @JsonProperty("show_alert")
    private Boolean showAlert;

    private String url;

    @JsonProperty("cache_time")
    private Integer cacheTime;
}
