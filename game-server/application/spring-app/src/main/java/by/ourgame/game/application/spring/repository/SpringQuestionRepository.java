package by.ourgame.game.application.spring.repository;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.port.QuestionRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringQuestionRepository extends QuestionRepository, MongoRepository<Question, String> {
}
