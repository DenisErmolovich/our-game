package by.ourgame.game.usecase;

import by.ourgame.game.domain.entity.BaseWithAuthor;
import by.ourgame.game.usecase.exception.NotFoundException;
import by.ourgame.game.usecase.port.BaseRepository;
import reactor.core.publisher.Mono;

public class BaseUsecase<T extends BaseWithAuthor> {
    protected final BaseRepository<T> repository;

    public BaseUsecase(BaseRepository<T> repository) {
        this.repository = repository;
    }

    protected Mono<T> getNotFoundError(String id, String author) {
        var message = generateNotFoundMessage(id, author);
        var ex = new NotFoundException(message);
        return Mono.error(ex);
    }

    private String generateNotFoundMessage(String id, String author) {
        var template = "Can't find entity with id '%s' for user '%s'";
        return String.format(template, id, author);
    }
}
