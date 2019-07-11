package ourgame.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ourgame.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class JwtUtil {

    public String generateToken(User user) {
        return Jwts.builder()
                .setClaims(getClaims(user))
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
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

    private PrivateKey getPrivateKey() {
        PrivateKey privateKey;
        byte[] key = getPrivateKeyBytes();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Can't get private key for JWT: {}, {}\n{}",
                    e.getClass().getName(),
                    e.getMessage(),
                    e.getStackTrace());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Can't get private key for JWT.");
        }
        return privateKey;
    }

    private byte[] getPrivateKeyBytes() {
        try {
            InputStream keyIs = new ClassPathResource("private.key").getInputStream();
            byte[] key = keyIs.readAllBytes();
            keyIs.close();
            return  key;
        } catch (IOException e) {
            log.error("Can't read private key from file: {}, {}\n{}",
                    e.getClass().getName(),
                    e.getMessage(),
                    e.getStackTrace());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Can't read private key from file.");
        }
    }
}
