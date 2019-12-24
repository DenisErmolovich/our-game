package by.ourgame.game.usecase.port;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

    T save(T entity);

    Optional<T> findById(String id);

    List<T> findAll();

    void deleteById(String id);
}
