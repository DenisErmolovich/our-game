package by.ourgame.bot.repository;

import by.ourgame.bot.model.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface GameRepository extends ReactiveMongoRepository<Game, String> {
    Mono<Game> findByChat_Id(Integer id);
}
