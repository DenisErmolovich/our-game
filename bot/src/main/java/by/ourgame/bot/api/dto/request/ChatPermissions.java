package by.ourgame.bot.api.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ChatPermissions {
    @JsonProperty("can_send_messages")
    private Boolean canSendMessages;

    @JsonProperty("can_send_media_messages")
    private Boolean canSendMediaMessages;

    @JsonProperty("can_send_polls")
    private Boolean canSendPolls;

    @JsonProperty("can_send_other_messages")
    private Boolean canSendOtherMessages;

    @JsonProperty("can_add_web_page_previews")
    private Boolean canAddWebPagePreviews;

    @JsonProperty("can_change_info")
    private Boolean canChangeInfo;

    @JsonProperty("can_invite_users")
    private Boolean canInviteUsers;

    @JsonProperty("can_pin_messages")
    private Boolean canPinMessages;

}
