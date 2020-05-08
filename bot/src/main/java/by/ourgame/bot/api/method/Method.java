package by.ourgame.bot.api.method;

import by.ourgame.bot.api.client.ReactiveBotClient;
import by.ourgame.bot.api.dto.Response;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public abstract class Method<I, O> {
    private final WebClient client;
    private final String method;

    public Method(String method, ReactiveBotClient client) {
        this.method = checkMethod(method);
        this.client = client.getBaseWebClient();
    }

    public abstract ParameterizedTypeReference<Response<O>> getType();

    public Mono<O> perform(I body) {
        return client.post()
                .uri(method)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(getType())
                .map(Response::getResult);
    }

    private String checkMethod(String method) {
        return method;
    }
}
