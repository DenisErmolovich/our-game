package by.ourgame.game.usecase;

import by.ourgame.game.domain.entity.BaseWithAuthor;
import by.ourgame.game.usecase.exception.NotFoundException;
import by.ourgame.game.usecase.port.BaseRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BaseFindUsecase<T extends BaseWithAuthor> extends BaseUsecase<T> {

    public BaseFindUsecase(BaseRepository<T> repository) {
        super(repository);
    }

    public Mono<T> findByIdAndAuthor(String id, String author) {
        return repository
                .findByIdAndAuthor(id, author)
                .switchIfEmpty(getNotFoundError(id, author));
    }

    public Flux<T> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }
}
