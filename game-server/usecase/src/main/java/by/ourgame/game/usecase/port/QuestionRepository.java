package by.ourgame.game.usecase.port;

import by.ourgame.game.domain.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

    Question save(Question question);

    Optional<Question> findQuestionById(String id);

    List<Question> findAll();

    void deleteById(String id);
}
