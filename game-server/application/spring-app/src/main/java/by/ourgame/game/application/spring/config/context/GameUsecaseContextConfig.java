package by.ourgame.game.application.spring.config.context;

import by.ourgame.game.domain.entity.Game;
import by.ourgame.game.usecase.BaseDeleteUsecase;
import by.ourgame.game.usecase.BaseFindUsecase;
import by.ourgame.game.usecase.BaseSaveUsecase;
import by.ourgame.game.usecase.port.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameUsecaseContextConfig implements UsecaseContextConfig<Game> {

    @Bean("gameSaveUsecase")
    @Autowired
    @Override
    public BaseSaveUsecase<Game> saveUsecase(BaseRepository<Game> repository) {
        return new BaseSaveUsecase<>(repository);
    }

    @Bean("gameFindUsecase")
    @Autowired
    @Override
    public BaseFindUsecase<Game> findUsecase(BaseRepository<Game> repository) {
        return new BaseFindUsecase<>(repository);
    }

    @Bean("gameDeleteUsecase")
    @Autowired
    @Override
    public BaseDeleteUsecase<Game> deleteUsecase(BaseRepository<Game> repository) {
        return new BaseDeleteUsecase<>(repository);
    }
}
