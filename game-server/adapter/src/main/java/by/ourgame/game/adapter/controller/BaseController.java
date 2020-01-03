package by.ourgame.game.adapter.controller;

import by.ourgame.game.usecase.BaseDeleteUsecase;
import by.ourgame.game.usecase.BaseFindUsecase;
import by.ourgame.game.usecase.BaseSaveUsecase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BaseController<T> {
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

    public Mono<T> save(T entity) {
        return saveUsecase.save(entity);
    }

    public Mono<T> update(T entity) {
        return saveUsecase.save(entity);
    }

    public Flux<T> findAll() {
        return findUsecase.findAll();
    }

    public Mono<T> findById(String id) {
        return findUsecase.findById(id);
    }

    public Mono<Void> deleteById(String id)  {
        return deleteUsecase.deleteById(id);
    }
}
