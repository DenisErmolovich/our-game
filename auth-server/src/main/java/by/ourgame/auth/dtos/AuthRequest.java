package by.ourgame.auth.dtos;

import by.ourgame.auth.validators.AuthRequestValidator;
import lombok.Data;
import org.springframework.validation.Validator;

@Data
public class AuthRequest implements SelfValidatable {
    private String login;
    private char[] password;

    @Override
    public Validator getValidator() {
        return new AuthRequestValidator();
    }
}
