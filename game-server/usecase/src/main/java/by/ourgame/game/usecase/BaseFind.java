package by.ourgame.game.usecase;

import by.ourgame.game.usecase.port.BaseRepository;

import java.util.List;

public class BaseFind<T> extends BaseUsecase<T> {

    public BaseFind(BaseRepository<T> repository) {
        super(repository);
    }

    public T findById(String id) {
        return repository.findById(id).get();
    }

    public List<T> findAll() {
        return repository.findAll();
    }
}
