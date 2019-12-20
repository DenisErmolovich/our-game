package by.ourgame.game.usecase.question;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.port.QuestionRepository;

public class SaveQuestion extends BaseQuestionUsecase {

    public SaveQuestion(QuestionRepository repository) {
        super(repository);
    }

    public Question save(Question question) {
        return repository.save(question);
    }
}
