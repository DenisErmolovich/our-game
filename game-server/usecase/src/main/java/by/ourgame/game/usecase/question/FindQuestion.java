package by.ourgame.game.usecase.question;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.port.QuestionRepository;

import java.util.List;

public class FindQuestion extends BaseQuestionUsecase {

    public FindQuestion(QuestionRepository repository) {
        super(repository);
    }

    public Question findById(String id) {
        return repository.findQuestionById(id).get();
    }

    public List<Question> findAll() {
        return repository.findAll();
    }
}
