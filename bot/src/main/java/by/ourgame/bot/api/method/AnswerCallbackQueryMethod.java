package by.ourgame.bot.api.method;

import by.ourgame.bot.api.client.ReactiveBotClient;
import by.ourgame.bot.api.dto.Response;
import by.ourgame.bot.api.dto.request.AnswerCallbackQueryRequest;
import org.springframework.core.ParameterizedTypeReference;

public class AnswerCallbackQueryMethod extends Method<AnswerCallbackQueryRequest, Boolean> {

    public AnswerCallbackQueryMethod(ReactiveBotClient client) {
        super("/answerCallbackQuery", client);
    }

    @Override
    public ParameterizedTypeReference<Response<Boolean>> getType() {
        return new ParameterizedTypeReference<>() {};
    }
}
