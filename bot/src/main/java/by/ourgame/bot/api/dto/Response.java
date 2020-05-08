package by.ourgame.bot.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Response<T> {
    private Boolean ok;

    private String description;

    private T result;

    @JsonProperty("error_code")
    private Integer errorCode;

    private ResponseParameters parameters;
}
