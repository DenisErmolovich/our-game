package by.ourgame.game.usecase;

import by.ourgame.game.usecase.port.BaseRepository;

public class BaseDelete<T> extends BaseUsecase<T> {

    public BaseDelete(BaseRepository<T> repository) {
        super(repository);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
