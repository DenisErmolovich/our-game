package by.ourgame.bot.api.method;

import by.ourgame.bot.api.client.ReactiveBotClient;
import by.ourgame.bot.api.dto.Response;
import by.ourgame.bot.api.dto.request.DeleteMessageRequest;
import org.springframework.core.ParameterizedTypeReference;

public class DeleteMessageMethod extends Method<DeleteMessageRequest, Boolean> {

    public DeleteMessageMethod(ReactiveBotClient client) {
        super("/deleteMessage", client);
    }

    @Override
    public ParameterizedTypeReference<Response<Boolean>> getType() {
        return new ParameterizedTypeReference<>() {};
    }
}
