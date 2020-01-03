package by.ourgame.game.usecase;

import by.ourgame.game.usecase.port.BaseRepository;
import reactor.core.publisher.Mono;

public class BaseSaveUsecase<T> extends BaseUsecase<T> {

    public BaseSaveUsecase(BaseRepository<T> repository) {
        super(repository);
    }

    public Mono<T> save(T entity) {
        return repository.save(entity);
    }
}
