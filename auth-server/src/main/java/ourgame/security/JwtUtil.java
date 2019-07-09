package ourgame.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ourgame.models.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

@Component
@Slf4j
public class JwtUtil {

    public String generateToken(User user) {
        return Jwts.builder()
                .setClaims(getClaims(user))
                .signWith(getPrivateKey().get(), SignatureAlgorithm.RS256) //ToDo: Add custom exception
                .compact();
    }

    private Map<String, Object> getClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("jit", UUID.randomUUID());
        claims.put("iss", "our-game");
        claims.put("sub", "auth token");
        claims.put("iat", new Date()); //ToDo: Think about exp date
        claims.put("login", user.getLogin());
        claims.put("roles", user.getRoles());
        return claims;
    }

    private Optional<PrivateKey> getPrivateKey() {
        PrivateKey privateKey = null;
        try {
            File keyFile = new ClassPathResource("private.key").getFile();

            byte[] key = Files.readAllBytes(keyFile.toPath());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Can't get private key for JWT: {}, {}\n{}", e.getClass().getName(), e.getMessage(), e.getStackTrace());
        }
        return Optional.ofNullable(privateKey);
    }
}
