package by.ourgame.game.usecase.question;

import by.ourgame.game.usecase.port.QuestionRepository;

public class DeleteQuestion extends BaseQuestionUsecase {

    public DeleteQuestion(QuestionRepository repository) {
        super(repository);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
