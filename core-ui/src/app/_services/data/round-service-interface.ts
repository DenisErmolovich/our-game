import {OldRound} from '../../_models/old-round';
import {OldSuperRound} from '../../_models/old-super-round';

/**
 * @deprecated for removal
 */
export interface RoundServiceInterface {
  getRound(gameId, roundId): OldRound;
  getSuperRound(gameId): OldSuperRound;
}
