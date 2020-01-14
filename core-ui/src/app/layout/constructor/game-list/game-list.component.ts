import {Component, OnInit} from '@angular/core';
import {Game} from '../../../_models/game';
import {GameService} from '../../../_services/data/http/game.service';
import {BalloonErrorService} from '../../../_services/errors/balloon-error.service';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.scss']
})
export class GameListComponent implements OnInit {
  private _games: Array<Game>;

  constructor(
    private gameService: GameService,
    private errorService: BalloonErrorService
  ) { }

  ngOnInit() {
    this.getGames();
  }


  get games(): Array<Game> {
    return this._games;
  }

  onReloadData(): void {
    this.getGames();
  }

  private getGames(): void {
    this.gameService.findAll().subscribe(
      response => this._games = response,
      error => this.errorService.sendError(error.error.error)
    );
  }
}
