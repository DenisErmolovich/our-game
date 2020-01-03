package by.ourgame.game.usecase.port;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseRepository<T> {

    <S extends T> Mono<S> save(S entity);

    Mono<T> findById(String id);

    Flux<T> findAll();

    Mono<Void> deleteById(String id);
}
