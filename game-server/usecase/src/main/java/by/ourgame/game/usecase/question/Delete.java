package by.ourgame.game.usecase.question;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.BaseUsecase;
import by.ourgame.game.usecase.port.BaseRepository;

public class Delete extends BaseUsecase<Question> {

    public Delete(BaseRepository<Question> repository) {
        super(repository);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
