import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Game} from '../../../../_models/game';
import {GameService} from '../../../../_services/data/http/game.service';
import {BalloonErrorService} from '../../../../_services/errors/balloon-error.service';

@Component({
  selector: 'app-game-row',
  templateUrl: './game-row.component.html',
  styleUrls: ['./game-row.component.scss']
})
export class GameRowComponent implements OnInit {
  @Input() game: Game;
  @Output() reloadData = new EventEmitter<void>();

  constructor(
    private gameService: GameService,
    private errorService: BalloonErrorService
  ) { }

  ngOnInit() {
  }

  delete(id: string) {
    this.gameService.deleteById(id).subscribe(
      response => this.reloadData.emit(),
      error => this.errorService.sendError(JSON.stringify(error))
    );
  }
}
