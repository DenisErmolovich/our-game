package by.ourgame.game.application.spring.controller;

import by.ourgame.game.adapter.controller.BaseController;
import by.ourgame.game.usecase.BaseDelete;
import by.ourgame.game.usecase.BaseFind;
import by.ourgame.game.usecase.BaseSave;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseSpringController<T> extends BaseController<T> {

    public BaseSpringController(BaseSave<T> save,
                                    BaseFind<T> find,
                                    BaseDelete<T> delete) {
        super(save, find, delete);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Override
    public T save(@RequestBody T entity) {
        return super.save(entity);
    }

    @GetMapping
    @Override
    public List<T> findAll() {
        return super.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public T findById(@PathVariable String id) {
        return super.findById(id);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteById(@PathVariable String id) {
        super.deleteById(id);
    }
}
