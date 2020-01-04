import {Roles} from '../_enums/roles.enum';

export interface User {
  token: string;
  login: string;
  roles: Array<Roles>;
}
