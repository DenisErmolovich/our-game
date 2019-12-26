package by.ourgame.game.adapter.controller;

import by.ourgame.game.usecase.BaseDeleteUsecase;
import by.ourgame.game.usecase.BaseFindUsecase;
import by.ourgame.game.usecase.BaseSaveUsecase;

import java.util.List;

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

    public T save(T entity) {
        return saveUsecase.save(entity);
    }

    public List<T> findAll() {
        return findUsecase.findAll();
    }

    public T findById(String id) {
        return findUsecase.findById(id);
    }

    public void deleteById(String id) {
        deleteUsecase.deleteById(id);
    }
}
