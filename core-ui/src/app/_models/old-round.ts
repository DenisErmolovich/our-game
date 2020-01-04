import {OldTopic} from './old-topic';

/**
 * @deprecated for removal
 */
export class OldRound {
  constructor(
    public id: string = null,
    public name: string = null,
    public topics: Array<OldTopic> = new Array<OldTopic>()
  ) {}
}
