import {BaseWithId} from './base-with-id';

export interface Game extends BaseWithId {
  name: string;
  authorLogin?: string;
}
