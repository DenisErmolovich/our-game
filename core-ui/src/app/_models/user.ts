import {Roles} from '../_enums/roles.enum';

export class User {
  constructor(
    public id: string = null,
    public name: string = null,
    public role: Roles = null,
    public score: number = null
  ) {}
}
