package ourgame.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
public class PasswordEncoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordEncoder.class);

    @Value("${security.password.encoder.iterations}")
    private int iterations;

    @Value("${security.password.encoder.keylength}")
    private int keyLength;

    public String encode(String password, String salt) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return Base64.getEncoder().encodeToString(res);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.error("{}: {}", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

    public boolean matches(String password, String salt, String hash) {
        return encode(password, salt).equals(hash);
    }
}
