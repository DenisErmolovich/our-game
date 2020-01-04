import { Injectable } from '@angular/core';
import {OldGame} from '../../../_models/old-game';
import {HttpClient} from '@angular/common/http';
import {BalloonErrorService} from '../../errors/balloon-error.service';
import {ErrorBase} from '../../../_models/error-base';
import {GameServiceInterface} from '../game-service-interface';
import {Router} from '@angular/router';

/**
 * @deprecated for removal
 */
@Injectable({
  providedIn: 'root'
})
export class GameService implements GameServiceInterface {

  constructor(
    private http: HttpClient,
    private errorService: BalloonErrorService,
    private router: Router
  ) { }

  public create(game: OldGame) {
    const games: Array<OldGame> = this.getAll();
    if (this.checkIsGameExist(game.id)) {
      this.errorService.sendError(`Игра с Id ${game.id} уже существует`);
      return null;
    } else {
      games.push(game);
      localStorage.setItem('games', JSON.stringify(games));
      return game;
    }
  }

  public delete(id: string): void {
    const games = this.getAll();
    const gameIndex = games.findIndex(item => item.id === id);
    games.splice(gameIndex, 1);
    localStorage.setItem('games', JSON.stringify(games));
  }

  public getAll(): Array<OldGame> {
    let games: Array<OldGame>;
    games = JSON.parse(localStorage.getItem('games')) as Array<OldGame>;
    if (!games) {
      games = new Array<OldGame>();
    }
    return games;
  }

  public getById(id: string): OldGame {
    const games = this.getAll();
    return games.find(game => game.id === id);
  }

  public update(game: OldGame) {
    return null;
  }

  /**
   * @deprecated for removal
   */
  public initGameFromJson(): void {
    let game: OldGame;
    this.http.get<OldGame>('assets/temp/initGame.json').subscribe(
      resp => {
        game = resp;
        if (this.isGameValid(game) && this.create(game)) {
          this.router.navigate(['game', game.id]);
        }
      },
      () => this.errorService.sendError('Ошибка при загрузке файла конфигураций!')
    );
  }

  /**
   * @deprecated for removal
   */
  private isGameValid(game: OldGame): boolean {
    if (!game.rounds || game.rounds.length !== game.settings.rounds) {
      this.errorService.sendError(`Неправильное колличство раундов в файле-конфигурации`);
      return false;
    }
    for (const round of game.rounds) {
      if (!round.topics || round.topics.length !== game.settings.topicsInRound) {
        this.errorService.sendError(`Неправильное колличство тем в раунде ${round.id} у файла-конфигурации`);
        return false;
      }
      for (const topic of round.topics) {
        if (!topic.questions || topic.questions.length !== game.settings.questionsInTopic) {
          this.errorService.sendError(`Неправильное колличство вопросов в теме ${topic.id} у файла-конфигурации`);
          return false;
        }
      }
    }
    if (!game.superRound || game.superRound.questions.length !== game.settings.superQuestions) {
      this.errorService.sendError(`Неправильное колличство вопросов в супер раунде у файла-конфигурации`);
      return false;
    }
    if (!game.settings.minPrice) {
      this.errorService.sendError(`Не указана минимальная стоимость в настройках у файла-конфигурации`);
      return false;
    }

    return true;
  }

  /**
   * @deprecated for removal
   */
  private checkIsGameExist(id: string): boolean {
    const games = this.getAll();
    if (games && games.find(game => game.id === id)) {
      return true;
    } else {
      return false;
    }
  }
}
