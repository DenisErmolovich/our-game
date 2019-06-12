package ourgame.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ourgame.models.User;

import java.util.*;

@Component
public class JwtUtil {
    @Value("${security.jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("login", user.getLogin());
        claims.put("roles", new ArrayList<>(user.getRoles()));
        return Jwts.builder()
                .setClaims(claims)
                .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret)))
                .compact();
    }
}
