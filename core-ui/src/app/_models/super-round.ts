import {Question} from './question';

export class SuperRound {
  constructor(
    public id: string = null,
    public questions: Array<Question> = new Array<Question>()
  ) {}
}
