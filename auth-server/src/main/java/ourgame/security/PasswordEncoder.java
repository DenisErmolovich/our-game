package ourgame.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

@Component
@Slf4j
public class PasswordEncoder {
    @Value("${security.password.encoder.iterations}")
    private int iterations;

    @Value("${security.password.encoder.keylength}")
    private int keyLength;

    public String encode(char[] password, String salt) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt.getBytes(), iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return Base64.getEncoder().encodeToString(res);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("{}: {}", e.getMessage(), e.getStackTrace());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Can't encode the password.");
        } finally {
            Arrays.fill(password, '0');
        }
    }

    public boolean matches(char[] password, String salt, String hash) {
        return encode(password, salt).equals(hash);
    }
}
