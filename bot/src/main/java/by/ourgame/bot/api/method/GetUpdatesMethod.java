package by.ourgame.bot.api.method;

import by.ourgame.bot.api.client.ReactiveBotClient;
import by.ourgame.bot.api.dto.Response;
import by.ourgame.bot.api.dto.Update;
import by.ourgame.bot.api.dto.request.GetUpdatesRequest;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class GetUpdatesMethod extends Method<GetUpdatesRequest, List<Update>> {

    public GetUpdatesMethod(ReactiveBotClient client) {
        super("/getUpdates", client);
    }

    @Override
    public ParameterizedTypeReference<Response<List<Update>>> getType() {
        return new ParameterizedTypeReference<>() {};
    }
}
