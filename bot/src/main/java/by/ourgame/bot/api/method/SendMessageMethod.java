package by.ourgame.bot.api.method;

import by.ourgame.bot.api.client.ReactiveBotClient;
import by.ourgame.bot.api.dto.Message;
import by.ourgame.bot.api.dto.Response;
import by.ourgame.bot.api.dto.request.SendMessageRequest;
import org.springframework.core.ParameterizedTypeReference;

public class SendMessageMethod extends Method<SendMessageRequest, Message> {

    public SendMessageMethod(ReactiveBotClient client) {
        super("/sendMessage", client);
    }

    @Override
    public ParameterizedTypeReference<Response<Message>> getType() {
        return new ParameterizedTypeReference<>() {};
    }
}
