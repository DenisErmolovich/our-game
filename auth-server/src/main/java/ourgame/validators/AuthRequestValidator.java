package ourgame.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ourgame.dtos.AuthRequest;

public class AuthRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "login", "auth.login.empty", "Login can't be empty!");
        ValidationUtils.rejectIfEmpty(errors, "password", "auth.password.empty", "Password can't be empty!");
    }
}
