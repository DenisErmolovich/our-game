package by.ourgame.bot.api.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class GetUpdatesRequest {
    private Integer offset;

    private Integer limit;

    private Integer timeout;

    @JsonProperty("allowed_updates")
    private List<String> allowed_updates;
}
