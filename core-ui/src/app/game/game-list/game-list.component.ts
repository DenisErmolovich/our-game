import { Component, OnInit } from '@angular/core';
import {GameService} from '../../_services/data/local-storage/game.service';
import {OldGame} from '../../_models/old-game';
import {Router} from '@angular/router';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.scss']
})
export class GameListComponent implements OnInit {
  public games: Array<OldGame>;

  constructor(
    private gameService: GameService,
    private router: Router
  ) { }

  ngOnInit() {
    this.games = this.gameService.getAll();
  }

  public continueGame(gameId: string) {
    this.router.navigate(['game', gameId]);
  }

  public deleteGame(gameId: string): void {
    this.gameService.delete(gameId);
    this.games = this.gameService.getAll();
  }

}
