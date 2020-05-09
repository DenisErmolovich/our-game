package by.ourgame.game.domain.entity;

import lombok.Data;

@Data
public abstract class BaseWithAuthor {
    private String id;
    private String author;
}
