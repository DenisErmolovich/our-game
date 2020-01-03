package by.ourgame.game.application.spring.controller;

import by.ourgame.game.adapter.controller.BaseController;
import by.ourgame.game.usecase.BaseDeleteUsecase;
import by.ourgame.game.usecase.BaseFindUsecase;
import by.ourgame.game.usecase.BaseSaveUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class BaseSpringController<T> extends BaseController<T> {

    public BaseSpringController(BaseSaveUsecase<T> saveUsecase,
                                BaseFindUsecase<T> findUsecase,
                                BaseDeleteUsecase<T> deleteUsecase) {
        super(saveUsecase, findUsecase, deleteUsecase);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Override
    public Mono<T> save(@RequestBody T entity) {
        return super.save(entity);
    }

    @PutMapping
    @Override
    public Mono<T> update(@RequestBody T entity) {
        return super.update(entity);
    }

    @GetMapping
    @Override
    public Flux<T> findAll() {
        return super.findAll();
    }

    @GetMapping("/{id}")
    @Override
    public Mono<T> findById(@PathVariable String id) {
        return super.findById(id);
    }

    @DeleteMapping("/{id}")
    @Override
    public Mono<Void> deleteById(@PathVariable String id) {
        return super.deleteById(id);
    }
}
