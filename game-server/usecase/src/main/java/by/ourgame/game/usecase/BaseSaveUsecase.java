package by.ourgame.game.usecase;

import by.ourgame.game.domain.entity.BaseWithAuthor;
import by.ourgame.game.usecase.exception.NotFoundException;
import by.ourgame.game.usecase.port.BaseRepository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import java.util.Objects;

public class BaseSaveUsecase<T extends BaseWithAuthor> extends BaseUsecase<T> {

    public BaseSaveUsecase(BaseRepository<T> repository) {
        super(repository);
    }

    public Mono<T> save(T entity, String author) {
        entity.setAuthor(author);
        return repository.save(entity);
    }

    public Mono<T> update(T entity, String author) {
        var id = entity.getId();
        return repository
                .findByIdAndAuthor(id, author)
                .switchIfEmpty(getNotFoundError(id, author))
                .flatMap(entityFromDb -> save(entity, author));
    }
}
