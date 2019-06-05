package ourgame.dtos;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
