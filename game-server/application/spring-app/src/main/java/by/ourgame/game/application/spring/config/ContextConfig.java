package by.ourgame.game.application.spring.config;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.BaseDelete;
import by.ourgame.game.usecase.BaseFind;
import by.ourgame.game.usecase.port.BaseRepository;
import by.ourgame.game.usecase.question.Save;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {

    @Bean
    @Autowired
    public Save saveQuestion(BaseRepository<Question> questionRepository) {
        return new Save(questionRepository);
    }

    @Bean
    @Autowired
    public BaseFind<Question> findQuestion(BaseRepository<Question> questionRepository) {
        return new BaseFind<>(questionRepository);
    }

    @Bean
    @Autowired
    public BaseDelete<Question> deleteQuestion(BaseRepository<Question> questionRepository) {
        return new BaseDelete<>(questionRepository);
    }
}
