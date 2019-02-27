import {Round} from '../../_models/round';
import {SuperRound} from '../../_models/super-round';

export interface RoundServiceInterface {
  getRound(gameId, roundId): Round;
  getSuperRound(gameId): SuperRound;
}
