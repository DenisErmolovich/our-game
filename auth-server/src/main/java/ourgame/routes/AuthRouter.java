package ourgame.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import ourgame.handlers.AuthHandler;

@Configuration
public class AuthRouter {

    @Bean
    public RouterFunction<ServerResponse> route(AuthHandler authHandler) {
        return RouterFunctions.route(
                RequestPredicates
                        .POST("/token")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                authHandler::getToken
        );
    }
}
