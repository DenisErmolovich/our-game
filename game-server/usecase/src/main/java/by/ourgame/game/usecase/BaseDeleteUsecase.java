package by.ourgame.game.usecase;

import by.ourgame.game.domain.entity.BaseWithAuthor;
import by.ourgame.game.usecase.port.BaseRepository;
import reactor.core.publisher.Mono;

public class BaseDeleteUsecase<T extends BaseWithAuthor> extends BaseUsecase<T> {

    public BaseDeleteUsecase(BaseRepository<T> repository) {
        super(repository);
    }

    public Mono<Void> deleteById(String id, String author) {
        return repository
                .findByIdAndAuthor(id, author)
                .switchIfEmpty(getNotFoundError(id, author))
                .flatMap(entity -> repository.deleteById(id));
    }
}
