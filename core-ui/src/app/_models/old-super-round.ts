import {OldQuestion} from './old-question';

/**
 * @deprecated for removal
 */
export class OldSuperRound {
  constructor(
    public id: string = null,
    public questions: Array<OldQuestion> = new Array<OldQuestion>()
  ) {}
}
