import {User} from './user';
import {Round} from './round';
import {SuperRound} from './super-round';
import {GameSettings} from './game-settings';

export class Game {
  constructor(
    public id: string = null,
    public name: string = null,
    public author: User = null,
    public players: Array<User> = new Array<User>(),
    public rounds: Array<Round> = new Array<Round>(),
    public superRound: SuperRound,
    public settings: GameSettings
  ) {}
}
