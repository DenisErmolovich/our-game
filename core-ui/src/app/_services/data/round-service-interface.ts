import {OldRound} from '../../_models/old-round';
import {OldSuperRound} from '../../_models/old-super-round';

export interface RoundServiceInterface {
  getRound(gameId, roundId): OldRound;
  getSuperRound(gameId): OldSuperRound;
}
