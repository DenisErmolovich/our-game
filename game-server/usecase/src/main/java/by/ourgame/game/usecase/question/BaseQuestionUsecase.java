package by.ourgame.game.usecase.question;

import by.ourgame.game.usecase.port.QuestionRepository;

public class BaseQuestionUsecase {
    protected final QuestionRepository repository;

    public BaseQuestionUsecase(QuestionRepository repository) {
        this.repository = repository;
    }
}
