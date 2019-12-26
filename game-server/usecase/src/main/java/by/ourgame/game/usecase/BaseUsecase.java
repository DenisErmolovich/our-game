package by.ourgame.game.usecase;

import by.ourgame.game.usecase.port.BaseRepository;

public class BaseUsecase<T> {
    protected final BaseRepository<T> repository;

    public BaseUsecase(BaseRepository<T> repository) {
        this.repository = repository;
    }
}
