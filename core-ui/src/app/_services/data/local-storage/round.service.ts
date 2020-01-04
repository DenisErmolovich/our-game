import { Injectable } from '@angular/core';
import {RoundServiceInterface} from '../round-service-interface';
import {OldRound} from '../../../_models/old-round';
import {GameService} from './game.service';
import {OldSuperRound} from '../../../_models/old-super-round';

/**
 * @deprecated for removal
 */
@Injectable({
  providedIn: 'root'
})
export class RoundService implements RoundServiceInterface {

  constructor(
    private gameService: GameService
  ) { }

  public getRound(gameId, roundId): OldRound {
    return this.gameService.getById(gameId).rounds.find(round => round.id === roundId);
  }

  getSuperRound(gameId): OldSuperRound {
    return this.gameService.getById(gameId).superRound;
  }
}
