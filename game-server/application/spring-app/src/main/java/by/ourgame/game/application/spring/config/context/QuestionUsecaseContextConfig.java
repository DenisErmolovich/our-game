package by.ourgame.game.application.spring.config.context;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.BaseDeleteUsecase;
import by.ourgame.game.usecase.BaseFindUsecase;
import by.ourgame.game.usecase.BaseSaveUsecase;
import by.ourgame.game.usecase.port.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuestionUsecaseContextConfig implements UsecaseContextConfig<Question> {

    @Bean
    @Autowired
    @Override
    public BaseSaveUsecase<Question> saveUsecase(BaseRepository<Question> repository) {
        return new BaseSaveUsecase<>(repository);
    }

    @Bean
    @Autowired
    @Override
    public BaseFindUsecase<Question> findUsecase(BaseRepository<Question> repository) {
        return new BaseFindUsecase<>(repository);
    }

    @Bean
    @Autowired
    @Override
    public BaseDeleteUsecase<Question> deleteUsecase(BaseRepository<Question> repository) {
        return new BaseDeleteUsecase<>(repository);
    }
}
