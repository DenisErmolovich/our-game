package by.ourgame.bot.api.method;

import by.ourgame.bot.api.client.ReactiveBotClient;
import by.ourgame.bot.api.dto.Response;
import by.ourgame.bot.api.exeption.ReactiveBotHttpClientException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
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
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> processHttpError(clientResponse, "client"))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> processHttpError(clientResponse, "server"))
                .bodyToMono(getType())
                .map(Response::getResult);
    }

    private String checkMethod(String method) {
        return method;
    }

    private Mono<ReactiveBotHttpClientException> processHttpError(ClientResponse clientResponse, String description) {
        var messageTemplate = "Http %s error with status %s: %s";
        return clientResponse.bodyToMono(Response.class)
                .flatMap(response -> Mono.error(new ReactiveBotHttpClientException(String.format(messageTemplate,
                        description,
                        response.getErrorCode(),
                        response.getDescription()))));
    }
}
