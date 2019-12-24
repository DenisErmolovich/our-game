package by.ourgame.game.adapter.controller;

import by.ourgame.game.usecase.BaseDelete;
import by.ourgame.game.usecase.BaseFind;
import by.ourgame.game.usecase.BaseSave;

import java.util.List;

public class BaseController<T> {
    private final BaseSave<T> saveUsecase;
    private final BaseFind<T> findUsecase;
    private final BaseDelete<T> deleteUsecase;

    public BaseController(BaseSave<T> saveUsecase,
                          BaseFind<T> findUsecase,
                          BaseDelete<T> deleteUsecase) {
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
