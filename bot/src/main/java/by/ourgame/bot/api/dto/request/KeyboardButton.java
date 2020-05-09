package by.ourgame.bot.api.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

// ToDo: add skipped fields
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class KeyboardButton {
    private String text;

    @JsonProperty("request_contact")
    private Boolean requestContact;

    @JsonProperty("request_location")
    private Boolean requestLocation;
}
