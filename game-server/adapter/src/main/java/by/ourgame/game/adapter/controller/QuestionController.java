package by.ourgame.game.adapter.controller;

import by.ourgame.game.domain.entity.Question;
import by.ourgame.game.usecase.BaseDelete;
import by.ourgame.game.usecase.question.Find;
import by.ourgame.game.usecase.question.Save;

import java.util.List;

public class QuestionController {
    private final Save saveQuestion;
    private final Find findQuestion;
    private final BaseDelete<Question> deleteQuestion;

    public QuestionController(Save saveQuestion,
                              Find findQuestion,
                              BaseDelete<Question> deleteQuestion) {
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
