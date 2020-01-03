package by.ourgame.game.usecase;

import by.ourgame.game.usecase.port.BaseRepository;
import reactor.core.publisher.Mono;

public class BaseDeleteUsecase<T> extends BaseUsecase<T> {

    public BaseDeleteUsecase(BaseRepository<T> repository) {
        super(repository);
    }

    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
