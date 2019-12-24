package by.ourgame.game.usecase.question;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.BaseUsecase;
import by.ourgame.game.usecase.port.BaseRepository;

public class Save extends BaseUsecase<Question> {

    public Save(BaseRepository<Question> repository) {
        super(repository);
    }

    public Question save(Question question) {
        return repository.save(question);
    }
}
