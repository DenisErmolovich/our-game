package by.ourgame.game.adapter.controller;

import by.ourgame.game.domain.entity.BaseWithAuthor;
import by.ourgame.game.usecase.BaseDeleteUsecase;
import by.ourgame.game.usecase.BaseFindUsecase;
import by.ourgame.game.usecase.BaseSaveUsecase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BaseController<T extends BaseWithAuthor> {
    private final BaseSaveUsecase<T> saveUsecase;
    private final BaseFindUsecase<T> findUsecase;
    private final BaseDeleteUsecase<T> deleteUsecase;

    public BaseController(BaseSaveUsecase<T> saveUsecase,
                          BaseFindUsecase<T> findUsecase,
                          BaseDeleteUsecase<T> deleteUsecase) {
        this.saveUsecase = saveUsecase;
        this.findUsecase = findUsecase;
        this.deleteUsecase = deleteUsecase;
    }

    public Mono<T> save(T entity, String author) {
        return saveUsecase.save(entity, author);
    }

    public Mono<T> update(T entity, String author) {
        return saveUsecase.update(entity, author);
    }

    public Flux<T> findByAuthor(String author) {
        return findUsecase.findByAuthor(author);
    }

    public Mono<T> findByIdAndAuthor(String id, String author) {
        return findUsecase.findByIdAndAuthor(id, author);
    }

    public Mono<Void> deleteById(String id, String author)  {
        return deleteUsecase.deleteById(id, author);
    }
}
