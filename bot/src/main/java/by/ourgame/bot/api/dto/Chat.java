package by.ourgame.bot.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// ToDo: add skipped fields
@Data
public class Chat {
    private Integer id;

    private String type;

    private String title;

    private String username;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String description;
}
