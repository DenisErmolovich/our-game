import {OldUser} from './old-user';
import {OldRound} from './old-round';
import {OldSuperRound} from './old-super-round';
import {OldGameSettings} from './old-game-settings';

/**
 * @deprecated for removal
 */
export class OldGame {
  constructor(
    public id: string = null,
    public name: string = null,
    public author: OldUser = null,
    public players: Array<OldUser> = new Array<OldUser>(),
    public rounds: Array<OldRound> = new Array<OldRound>(),
    public superRound: OldSuperRound,
    public settings: OldGameSettings
  ) {}
}
