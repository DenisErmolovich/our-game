import { Injectable } from '@angular/core';
import {PlayerServiceInterface} from '../player-service-interface';
import {GameService} from './game.service';
import {User} from '../../../_models/user';
import {QuestionService} from './question.service';
import {ScoreUpdateService} from '../../component-communication/score-update.service';

@Injectable({
  providedIn: 'root'
})
export class PlayerService implements PlayerServiceInterface {

  constructor(
    private gameService: GameService,
    private questionService: QuestionService,
    private scoreUpdateService: ScoreUpdateService
  ) { }

  public getPlayersByGameId(gameId: string): Array<User> {
    return this.gameService.getById(gameId).players;
  }

  public updateScore(gameId: string, userId: string, price: number): void {
    const games = this.gameService.getAll();
    const game = games.find(item => item.id === gameId);
    const player = game.players.find(item => item.id === userId);
    if (!player.score) {
      player.score = 0;
    }
    player.score = player.score + price;
    localStorage.setItem('games', JSON.stringify(games));
    this.scoreUpdateService.sendData();
  }
}
