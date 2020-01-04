import {OldQuestion} from './old-question';

/**
 * @deprecated for removal
 */
export class OldTopic {
  constructor(
    public id: string = null,
    public name: string = null,
    public questions: Array<OldQuestion> = new Array<OldQuestion>()
  ) {}
}
