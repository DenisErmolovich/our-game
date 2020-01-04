import {Roles} from '../_enums/roles.enum';

/**
 * @deprecated for removal
 */
export class OldUser {
  constructor(
    public id: string = null,
    public name: string = null,
    public role: Roles = null,
    public score: number = null
  ) {}
}
