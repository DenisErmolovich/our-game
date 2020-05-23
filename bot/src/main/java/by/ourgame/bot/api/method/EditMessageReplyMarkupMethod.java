package by.ourgame.bot.api.method;

import by.ourgame.bot.api.client.ReactiveBotClient;
import by.ourgame.bot.api.dto.Message;
import by.ourgame.bot.api.dto.Response;
import by.ourgame.bot.api.dto.request.EditMessageReplyMarkupRequest;
import org.springframework.core.ParameterizedTypeReference;

public class EditMessageReplyMarkupMethod extends Method<EditMessageReplyMarkupRequest, Message> {

    public EditMessageReplyMarkupMethod(ReactiveBotClient client) {
        super("/editMessageReplyMarkup", client);
    }

    @Override
    public ParameterizedTypeReference<Response<Message>> getType() {
        return new ParameterizedTypeReference<>() {};
    }
}
