package by.ourgame.game.usecase.question;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.BaseUsecase;
import by.ourgame.game.usecase.port.BaseRepository;

import java.util.List;

public class Find extends BaseUsecase<Question> {

    public Find(BaseRepository<Question> repository) {
        super(repository);
    }

    public Question findById(String id) {
        return repository.findById(id).get();
    }

    public List<Question> findAll() {
        return repository.findAll();
    }
}
