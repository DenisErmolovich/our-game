import {OldUser} from '../../_models/old-user';

export interface PlayerServiceInterface {
  getPlayersByGameId(gameId: string): Array<OldUser>;
  updateScore(gameId: string, userId: string, price: number): void;
}
