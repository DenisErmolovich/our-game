package by.ourgame.auth.dtos;

import org.slf4j.LoggerFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.server.ServerWebInputException;

import java.util.List;
import java.util.stream.Collectors;

public interface SelfValidatable {

    Validator getValidator();

    default void validate() {
        Validator validator = getValidator();
        Errors errors = new BeanPropertyBindingResult(this, this.getClass().getName());
        validator.validate(this, errors);
        if (errors.hasErrors()) {
            List<String> errorMessages = errors.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            LoggerFactory.getLogger(this.getClass()).error("Request validation problems: {}", errorMessages);
            throw new ServerWebInputException(String.join("; ", errorMessages));
        }
    }
}
