package ourgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AuthHandler {

    @Autowired
    UserRepository userRepository;

    public Mono<ServerResponse> hello(ServerRequest request) {
        Mono<User> user = request
                .bodyToMono(User.class)
                .map(userFromReq -> userRepository.getUserByLogin(userFromReq.getLogin()).orElseGet(() -> new User()));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(user, User.class);
    }
}
