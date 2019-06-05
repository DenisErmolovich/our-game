package ourgame.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import ourgame.dtos.AuthRequest;
import ourgame.dtos.AuthResponse;
import ourgame.models.User;
import ourgame.repositories.UserRepository;
import reactor.core.publisher.Mono;

import javax.validation.Validator;

@Component
public class AuthHandler {
    private UserRepository userRepository;
    private Validator validator;

    public AuthHandler(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    public Mono<ServerResponse> getToken(ServerRequest request) {
        Mono<AuthResponse> authResponse = request
                .bodyToMono(AuthRequest.class)
                .map(authRequest -> {
                    if (!validator.validate(authRequest).isEmpty()) {
                        throw new IllegalArgumentException(validator.validate(authRequest).toString());
                    }
                    User user = userRepository.getUserByLogin(authRequest.getLogin()).get();
                    return new AuthResponse().setToken(user.getPassword());
                });
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authResponse, AuthResponse.class);
    }
}
