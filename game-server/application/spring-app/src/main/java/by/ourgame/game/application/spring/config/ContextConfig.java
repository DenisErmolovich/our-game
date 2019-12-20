package by.ourgame.game.application.spring.config;

import by.ourgame.game.usecase.port.QuestionRepository;
import by.ourgame.game.usecase.question.DeleteQuestion;
import by.ourgame.game.usecase.question.FindQuestion;
import by.ourgame.game.usecase.question.SaveQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {

    @Bean
    @Autowired
    public SaveQuestion saveQuestion(QuestionRepository questionRepository) {
        return new SaveQuestion(questionRepository);
    }

    @Bean
    @Autowired
    public FindQuestion findQuestion(QuestionRepository questionRepository) {
        return new FindQuestion(questionRepository);
    }

    @Bean
    @Autowired
    public DeleteQuestion deleteQuestion(QuestionRepository questionRepository) {
        return new DeleteQuestion(questionRepository);
    }
}
