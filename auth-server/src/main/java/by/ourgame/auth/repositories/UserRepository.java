package by.ourgame.auth.repositories;

import by.ourgame.auth.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> getUserByLogin(String login);
}
