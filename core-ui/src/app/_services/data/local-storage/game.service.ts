import { Injectable } from '@angular/core';
import {Game} from '../../../_models/game';
import {HttpClient} from '@angular/common/http';
import {BalloonErrorService} from '../../errors/balloon-error.service';
import {ErrorBase} from '../../../_models/error-base';
import {GameServiceInterface} from '../game-service-interface';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class GameService implements GameServiceInterface {

  constructor(
    private http: HttpClient,
    private errorService: BalloonErrorService,
    private router: Router
  ) { }

  public create(game: Game) {
    const games: Array<Game> = this.getAll();
    if (this.checkIsGameExist(game.id)) {
      this.sendError(`Игра с Id ${game.id} уже существует`);
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

  public getAll(): Array<Game> {
    let games: Array<Game>;
    games = JSON.parse(localStorage.getItem('games')) as Array<Game>;
    if (!games) {
      games = new Array<Game>();
    }
    return games;
  }

  public getById(id: string): Game {
    const games = this.getAll();
    return games.find(game => game.id === id);
  }

  public update(game: Game) {
    return null;
  }

  public initGameFromJson(): void {
    let game: Game;
    this.http.get<Game>('assets/temp/initGame.json').subscribe(
      resp => {
        game = resp;
        if (this.isGameValid(game) && this.create(game)) {
          this.router.navigate(['game', game.id]);
        }
      },
      () => this.sendError('Ошибка при загрузке файла конфигураций!')
    );
  }

  private isGameValid(game: Game): boolean {
    if (!game.rounds || game.rounds.length !== game.settings.rounds) {
      this.sendError(`Неправильное колличство раундов в файле-конфигурации`);
      return false;
    }
    for (const round of game.rounds) {
      if (!round.topics || round.topics.length !== game.settings.topicsInRound) {
        this.sendError(`Неправильное колличство тем в раунде ${round.id} у файла-конфигурации`);
        return false;
      }
      for (const topic of round.topics) {
        if (!topic.questions || topic.questions.length !== game.settings.questionsInTopic) {
          this.sendError(`Неправильное колличство вопросов в теме ${topic.id} у файла-конфигурации`);
          return false;
        }
      }
    }
    if (!game.superRound || game.superRound.questions.length !== game.settings.superQuestions) {
      this.sendError(`Неправильное колличство вопросов в супер раунде у файла-конфигурации`);
      return false;
    }
    if (!game.settings.minPrice) {
      this.sendError(`Не указана минимальная стоимость в настройках у файла-конфигурации`);
      return false;
    }

    return true;
  }

  private checkIsGameExist(id: string): boolean {
    const games = this.getAll();
    if (games && games.find(game => game.id === id)) {
      return true;
    } else {
      return false;
    }
  }

  private sendError(errorText: string): void {
    const error = new ErrorBase();
    error.description = errorText;
    this.errorService.setData(error);
    this.errorService.sendData();
  }
}
