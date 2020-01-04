import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {GameService} from '../../../../_services/data/local-storage/game.service';
import {OldGame} from '../../../../_models/old-game';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  public game: OldGame;

  constructor(
    private activateRouter: ActivatedRoute,
    private router: Router,
    private gameService: GameService
  ) { }

  ngOnInit() {
   const gameId = this.activateRouter.snapshot.paramMap.get('gameId');
   this.game = this.gameService.getById(gameId);
  }

  public toRound(gameId: string, roundId: string): void {
    this.router.navigate(['game', gameId, 'round', roundId]);
  }

  public toSuperRound(gameId: string): void {
    this.router.navigate(['game', gameId, 'round', 'super']);
  }

}
