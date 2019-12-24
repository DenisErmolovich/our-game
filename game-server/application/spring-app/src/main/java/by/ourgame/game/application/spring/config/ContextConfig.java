package by.ourgame.game.application.spring.config;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.port.BaseRepository;
import by.ourgame.game.usecase.question.Delete;
import by.ourgame.game.usecase.question.Find;
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
    public Find findQuestion(BaseRepository<Question> questionRepository) {
        return new Find(questionRepository);
    }

    @Bean
    @Autowired
    public Delete deleteQuestion(BaseRepository<Question> questionRepository) {
        return new Delete(questionRepository);
    }
}
