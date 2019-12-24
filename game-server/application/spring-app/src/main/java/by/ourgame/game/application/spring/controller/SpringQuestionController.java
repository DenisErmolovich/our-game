package by.ourgame.game.application.spring.controller;

import by.ourgame.game.adapter.controller.QuestionController;
import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.BaseDelete;
import by.ourgame.game.usecase.BaseFind;
import by.ourgame.game.usecase.question.Save;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class SpringQuestionController extends QuestionController {

    @Autowired
    public SpringQuestionController(Save saveQuestion,
                                    BaseFind<Question> findQuestion,
                                    BaseDelete<Question> deleteQuestion) {
        super(saveQuestion, findQuestion, deleteQuestion);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Override
    public Question save(@RequestBody Question question) {
        return super.save(question);
    }

    @GetMapping
    @Override
    public List<Question> findAll() {
        return super.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public Question findById(@PathVariable String id) {
        return super.findById(id);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable String id) {
        super.deleteById(id);
    }
}
