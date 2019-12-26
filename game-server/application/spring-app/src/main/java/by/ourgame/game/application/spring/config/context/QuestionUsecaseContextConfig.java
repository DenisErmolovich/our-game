package by.ourgame.game.application.spring.config.context;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.BaseDelete;
import by.ourgame.game.usecase.BaseFind;
import by.ourgame.game.usecase.BaseSave;
import by.ourgame.game.usecase.port.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuestionUsecaseContextConfig implements UsecaseContextConfig<Question> {

    @Bean
    @Autowired
    @Override
    public BaseSave<Question> save(BaseRepository<Question> repository) {
        return new BaseSave<>(repository);
    }

    @Bean
    @Autowired
    @Override
    public BaseFind<Question> find(BaseRepository<Question> repository) {
        return new BaseFind<>(repository);
    }

    @Bean
    @Autowired
    @Override
    public BaseDelete<Question> delete(BaseRepository<Question> repository) {
        return new BaseDelete<>(repository);
    }
}
