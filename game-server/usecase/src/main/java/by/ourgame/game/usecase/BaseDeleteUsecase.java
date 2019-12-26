package by.ourgame.game.usecase;

import by.ourgame.game.usecase.port.BaseRepository;

public class BaseDeleteUsecase<T> extends BaseUsecase<T> {

    public BaseDeleteUsecase(BaseRepository<T> repository) {
        super(repository);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
