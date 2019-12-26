package by.ourgame.game.usecase;

import by.ourgame.game.usecase.port.BaseRepository;

import java.util.List;

public class BaseFindUsecase<T> extends BaseUsecase<T> {

    public BaseFindUsecase(BaseRepository<T> repository) {
        super(repository);
    }

    public T findById(String id) {
        return repository.findById(id).get();
    }

    public List<T> findAll() {
        return repository.findAll();
    }
}
