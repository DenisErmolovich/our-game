package by.ourgame.bot.api.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

// ToDo: add skipped fields
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class InlineKeyboardButton {
    private String text;

    private String url;

    @JsonProperty("callback_data")
    private String callbackData;

    @JsonProperty("switch_inline_query")
    private String switchInlineQuery;

    @JsonProperty("switch_inline_query_current_chat")
    private String switchInlineQueryCurrentChat;

    @JsonProperty("pay")
    private Boolean pay;
}
