package by.ourgame.game.adapter.controller;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.question.DeleteQuestion;
import by.ourgame.game.usecase.question.FindQuestion;
import by.ourgame.game.usecase.question.SaveQuestion;

import java.util.List;

public class QuestionController {
    private final SaveQuestion saveQuestion;
    private final FindQuestion findQuestion;
    private final DeleteQuestion deleteQuestion;

    public QuestionController(SaveQuestion saveQuestion,
                              FindQuestion findQuestion,
                              DeleteQuestion deleteQuestion) {
        this.saveQuestion = saveQuestion;
        this.findQuestion = findQuestion;
        this.deleteQuestion = deleteQuestion;
    }

    public Question save(Question question) {
        return saveQuestion.save(question);
    }

    public List<Question> findAll() {
        return findQuestion.findAll();
    }

    public Question findById(String id) {
        return findQuestion.findById(id);
    }

    public void deleteById(String id) {
        deleteQuestion.deleteById(id);
    }
}
