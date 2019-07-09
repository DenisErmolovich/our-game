package ourgame.handlers;

import lombok.extern.slf4j.Slf4j;
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
import ourgame.security.JwtUtil;
import ourgame.security.PasswordEncoder;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthHandler {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    public AuthHandler(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Mono<ServerResponse> getToken(ServerRequest request) {
        Mono<AuthResponse> authResponse = request
                .bodyToMono(AuthRequest.class)
                .map(authRequest -> {
                    authRequest.validate();
                    User user = userRepository.getUserByLogin(authRequest.getLogin()).orElse(null);
                    if (null == user ||
                            !passwordEncoder.matches(authRequest.getPassword(), user.getSalt(), user.getPassword())) {
                        log.error("Unsuccessful attempt of authentication for user: {}", authRequest.getLogin());
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid login or password.");
                    }
                    return new AuthResponse().setToken(jwtUtil.generateToken(user));
                });
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authResponse, AuthResponse.class);
    }
}
