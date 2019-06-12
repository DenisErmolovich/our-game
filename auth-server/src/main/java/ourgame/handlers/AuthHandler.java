package ourgame.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import ourgame.dtos.AuthRequest;
import ourgame.dtos.AuthResponse;
import ourgame.models.User;
import ourgame.repositories.UserRepository;
import ourgame.security.PasswordEncoder;
import reactor.core.publisher.Mono;

@Component
public class AuthHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthHandler.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AuthHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Mono<ServerResponse> getToken(ServerRequest request) {
        Mono<AuthResponse> authResponse = request
                .bodyToMono(AuthRequest.class)
                .map(authRequest -> {
                    authRequest.validate();
                    User user = userRepository.getUserByLogin(authRequest.getLogin()).orElse(null);
                    if (null == user ||
                            !passwordEncoder.matches(authRequest.getPassword(), user.getSalt(), user.getPassword())) {
                        LOGGER.error("Unsuccessful attempt of authentication for user: {}", authRequest.getLogin());
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid login or password.");
                    }
                    return new AuthResponse().setToken(user.getPassword());
                });
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authResponse, AuthResponse.class);
    }
}
