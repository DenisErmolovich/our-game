package by.ourgame.game.application.spring.controller;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.BaseDelete;
import by.ourgame.game.usecase.BaseFind;
import by.ourgame.game.usecase.BaseSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class SpringQuestionController extends BaseSpringController<Question> {

    @Autowired
    public SpringQuestionController(BaseSave<Question> saveQuestion,
                                    BaseFind<Question> findQuestion,
                                    BaseDelete<Question> deleteQuestion) {
        super(saveQuestion, findQuestion, deleteQuestion);
    }
}
