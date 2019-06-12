package ourgame.dtos;

import lombok.Data;
import org.springframework.validation.Validator;
import ourgame.validators.AuthRequestValidator;

@Data
public class AuthRequest implements SelfValidatable {
    private String login;
    private String password;

    @Override
    public Validator getValidator() {
        return new AuthRequestValidator();
    }
}
