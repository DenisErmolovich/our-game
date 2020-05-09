package by.ourgame.game.usecase.port;

import by.ourgame.game.domain.entity.BaseWithAuthor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseRepository<T extends BaseWithAuthor> {

    <S extends T> Mono<S> save(S entity);

    Mono<T> findByIdAndAuthor(String id, String author);

    Flux<T> findByAuthor(String author);

    Mono<Void> deleteById(String id);
}
