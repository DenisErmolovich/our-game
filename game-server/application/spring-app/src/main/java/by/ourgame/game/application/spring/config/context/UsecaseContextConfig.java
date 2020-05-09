package by.ourgame.game.application.spring.config.context;

import by.ourgame.game.domain.entity.BaseWithAuthor;
import by.ourgame.game.usecase.BaseDeleteUsecase;
import by.ourgame.game.usecase.BaseFindUsecase;
import by.ourgame.game.usecase.BaseSaveUsecase;
import by.ourgame.game.usecase.port.BaseRepository;

public interface UsecaseContextConfig<T extends BaseWithAuthor> {

    BaseSaveUsecase<T> saveUsecase(BaseRepository<T> repository);

    BaseFindUsecase<T> findUsecase(BaseRepository<T> repository);

    BaseDeleteUsecase<T> deleteUsecase(BaseRepository<T> repository);
}
