import {Game} from '../../_models/game';

export interface GameServiceInterface {
  create(game: Game): Game;
  delete(id: string): void;
  getAll(): Array<Game>;
  getById(id: string): Game;
  update(game: Game): Game;
}
