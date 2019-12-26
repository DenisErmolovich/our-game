package by.ourgame.game.usecase;

import by.ourgame.game.usecase.port.BaseRepository;

public class BaseSaveUsecase<T> extends BaseUsecase<T> {

    public BaseSaveUsecase(BaseRepository<T> repository) {
        super(repository);
    }

    public T save(T entity) {
        return repository.save(entity);
    }
}
