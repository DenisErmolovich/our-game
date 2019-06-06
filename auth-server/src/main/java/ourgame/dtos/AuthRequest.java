package ourgame.dtos;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthRequest {
    private String login;
    private String password;

    public void validate() {
        List<String> errors = new ArrayList<>();
        if (StringUtils.isEmpty(login)) {
            errors.add("Login can't be empty!");
        }
        if (StringUtils.isEmpty(password)) {
            errors.add("Password can't be empty!");
        }
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("; ", errors));
        }
    }
}
