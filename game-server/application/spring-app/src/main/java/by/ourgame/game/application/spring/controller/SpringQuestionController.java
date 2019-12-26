package by.ourgame.game.application.spring.controller;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.BaseDeleteUsecase;
import by.ourgame.game.usecase.BaseFindUsecase;
import by.ourgame.game.usecase.BaseSaveUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class SpringQuestionController extends BaseSpringController<Question> {

    @Autowired
    public SpringQuestionController(BaseSaveUsecase<Question> questionSaveUsecase,
                                    BaseFindUsecase<Question> questionFindUsecase,
                                    BaseDeleteUsecase<Question> questionDeleteUsecase) {
        super(questionSaveUsecase, questionFindUsecase, questionDeleteUsecase);
    }
}
