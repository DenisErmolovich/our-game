package by.ourgame.bot.api.method;

import by.ourgame.bot.api.client.ReactiveBotClient;
import by.ourgame.bot.api.dto.Response;
import by.ourgame.bot.api.dto.request.SetChatPermissionsRequest;
import org.springframework.core.ParameterizedTypeReference;

public class SetChatPermissionsMethod extends Method<SetChatPermissionsRequest, Boolean> {

    public SetChatPermissionsMethod(ReactiveBotClient client) {
        super("/setChatPermissions", client);
    }

    @Override
    public ParameterizedTypeReference<Response<Boolean>> getType() {
        return new ParameterizedTypeReference<Response<Boolean>>() {};
    }
}
