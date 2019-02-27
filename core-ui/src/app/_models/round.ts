import {Topic} from './topic';

export class Round {
  constructor(
    public id: string = null,
    public name: string = null,
    public topics: Array<Topic> = new Array<Topic>()
  ) {}
}
