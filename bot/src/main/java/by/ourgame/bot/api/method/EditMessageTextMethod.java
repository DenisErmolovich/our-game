package by.ourgame.bot.api.method;

import by.ourgame.bot.api.client.ReactiveBotClient;
import by.ourgame.bot.api.dto.Message;
import by.ourgame.bot.api.dto.Response;
import by.ourgame.bot.api.dto.request.EditMessageTextRequest;
import org.springframework.core.ParameterizedTypeReference;

public class EditMessageTextMethod extends Method<EditMessageTextRequest, Message> {

    public EditMessageTextMethod(ReactiveBotClient client) {
        super("/editMessageText", client);
    }

    @Override
    public ParameterizedTypeReference<Response<Message>> getType() {
        return new ParameterizedTypeReference<>() {};
    }
}
