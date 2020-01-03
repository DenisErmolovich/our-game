package by.ourgame.game.application.spring.repository;

import by.ourgame.game.domain.entity.Game;
import by.ourgame.game.usecase.port.BaseRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringGameRepository extends BaseRepository<Game>, ReactiveMongoRepository<Game, String> {
}
