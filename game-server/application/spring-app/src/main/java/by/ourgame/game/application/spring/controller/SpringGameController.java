package by.ourgame.game.application.spring.controller;

import by.ourgame.game.domain.entity.Game;
import by.ourgame.game.usecase.BaseDeleteUsecase;
import by.ourgame.game.usecase.BaseFindUsecase;
import by.ourgame.game.usecase.BaseSaveUsecase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class SpringGameController extends BaseSpringController<Game> {

    public SpringGameController(BaseSaveUsecase<Game> gameSaveUsecase,
                                BaseFindUsecase<Game> gameFindUsecase,
                                BaseDeleteUsecase<Game> gameDeleteUsecase) {
        super(gameSaveUsecase, gameFindUsecase, gameDeleteUsecase);
    }
}
