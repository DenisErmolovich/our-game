package by.ourgame.auth.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class AuthResponse {
    private String token;
}
