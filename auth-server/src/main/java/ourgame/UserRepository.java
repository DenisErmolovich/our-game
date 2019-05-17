package ourgame;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UserRepository {
    public User getUserByLogin(String login) {
        Role[] roles = {Role.ADMIN, Role.PLAYER};
        return new User(login, "password", Arrays.stream(roles).collect(Collectors.toSet()));
    }
}
