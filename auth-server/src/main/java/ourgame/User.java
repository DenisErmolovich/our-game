package ourgame;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class User {
    @NonNull
    private String login;
    @NonNull
    private String password;
    @NonNull
    private Set<Role> roles;
}
