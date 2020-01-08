import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {PlayerService} from '../../_services/data/local-storage/player.service';
import {OldUser} from '../../_models/old-user';
import {ScoreUpdateService} from '../../_services/component-communication/score-update.service';

@Component({
  selector: 'app-layout-with-score',
  templateUrl: './layout-with-score.component.html',
  styleUrls: ['./layout-with-score.component.scss']
})
export class LayoutWithScoreComponent implements OnInit {
  private gameId: string;
  public players: Array<OldUser>;

  constructor(
    private router: ActivatedRoute,
    private playerService: PlayerService,
    private scoreUpdateService: ScoreUpdateService
  ) { }

  ngOnInit() {
    this.gameId = this.router.snapshot.paramMap.get('gameId');
    this.getPlayers(this.gameId);
    this.scoreUpdateService.dataTransferEvent$.subscribe(
      () => this.getPlayers(this.gameId)
    );
  }

  private getPlayers(gameId: string): void {
    this.players = this.playerService.getPlayersByGameId(gameId);
  }
}
