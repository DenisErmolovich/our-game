package by.ourgame.game.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Question extends BaseWithAuthor{
    private QuestionType type;
    private String topic;
    private String text;
    private String answer;
    private List<String> images;
    private String sound;
    private List<String> answerImages;
    private boolean answered;
}
