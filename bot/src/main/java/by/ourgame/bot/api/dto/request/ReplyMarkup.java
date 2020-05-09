package by.ourgame.bot.api.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ReplyMarkup {
    @JsonProperty("inline_keyboard")
    private List<List<InlineKeyboardButton>> inlineKeyboard;

    private List<List<KeyboardButton>> keyboard;

    @JsonProperty("resize_keyboard")
    private Boolean resizeKeyboard;

    @JsonProperty("one_time_keyboard")
    private Boolean oneTimeKeyboard;

    private Boolean selective;

    @JsonProperty("remove_keyboard")
    private Boolean removeKeyboard;

    @JsonProperty("force_reply")
    private Boolean forceReply;
}
