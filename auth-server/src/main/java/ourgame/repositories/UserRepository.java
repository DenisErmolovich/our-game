package ourgame.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ourgame.models.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> getUserByLogin(String login);
}
