package by.ourgame.game.usecase;

import by.ourgame.game.usecase.port.BaseRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BaseFindUsecase<T> extends BaseUsecase<T> {

    public BaseFindUsecase(BaseRepository<T> repository) {
        super(repository);
    }

    public Mono<T> findById(String id) {
        return repository.findById(id);
    }

    public Flux<T> findAll() {
        return repository.findAll();
    }
}
