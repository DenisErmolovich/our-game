package by.ourgame.game.application.spring.config.context;

import by.ourgame.game.usecase.BaseDelete;
import by.ourgame.game.usecase.BaseFind;
import by.ourgame.game.usecase.BaseSave;
import by.ourgame.game.usecase.port.BaseRepository;

public interface UsecaseContextConfig<T> {

    BaseSave<T> save(BaseRepository<T> repository);

    BaseFind<T> find(BaseRepository<T> repository);

    BaseDelete<T> delete(BaseRepository<T> repository);
}
