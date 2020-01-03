package by.ourgame.game.application.spring.repository;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.port.BaseRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringQuestionRepository extends BaseRepository<Question>, ReactiveMongoRepository<Question, String> {
}
