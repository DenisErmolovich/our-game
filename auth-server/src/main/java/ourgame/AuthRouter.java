package ourgame;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AuthRouter {

    @Bean
    public RouterFunction<ServerResponse> route(AuthHandler authHandler) {
        return RouterFunctions.route(
                RequestPredicates
                        .POST("/auth")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                authHandler::hello
        );
    }
}
