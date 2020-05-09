package by.ourgame.game.application.spring.controller;

import by.ourgame.game.adapter.controller.BaseController;
import by.ourgame.game.domain.entity.BaseWithAuthor;
import by.ourgame.game.usecase.BaseDeleteUsecase;
import by.ourgame.game.usecase.BaseFindUsecase;
import by.ourgame.game.usecase.BaseSaveUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class BaseSpringController<T extends BaseWithAuthor> extends BaseController<T> {

    public BaseSpringController(BaseSaveUsecase<T> saveUsecase,
                                BaseFindUsecase<T> findUsecase,
                                BaseDeleteUsecase<T> deleteUsecase) {
        super(saveUsecase, findUsecase, deleteUsecase);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Override
    public Mono<T> save(@RequestBody T entity,
                        @RequestHeader(value = "X-UserName", required = false) String author) {
        return super.save(entity, author);
    }

    @PutMapping
    @Override
    public Mono<T> update(@RequestBody T entity,
                          @RequestHeader(value = "X-UserName", required = false) String author) {
        return super.update(entity, author);
    }

    @GetMapping
    @Override
    public Flux<T> findByAuthor(
            @RequestHeader(value = "X-UserName", required = false) String author) {
        return super.findByAuthor(author);
    }

    @GetMapping("/{id}")
    @Override

    public Mono<T> findByIdAndAuthor(@PathVariable String id,
                            @RequestHeader(value = "X-UserName", required = false) String author) {
        return super.findByIdAndAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    @Override
    public Mono<Void> deleteById(@PathVariable String id,
                                 @RequestHeader(value = "X-UserName", required = false) String author) {
        return super.deleteById(id, author);
    }
}
