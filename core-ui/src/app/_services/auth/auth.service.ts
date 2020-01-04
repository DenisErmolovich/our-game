import {Injectable} from '@angular/core';
import {AuthResponse} from '../../_models/auth-response';
import {AuthHttpService} from './auth-http.service';
import {AuthRequest} from '../../_models/auth-request';
import {BalloonErrorService} from '../errors/balloon-error.service';
import {Router} from '@angular/router';
import {User} from '../../_models/user';
import {JwtHelperService} from "@auth0/angular-jwt";
import {Roles} from "../../_enums/roles.enum";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private static readonly AUTH_PROP_NAME = 'user';
  private _user: User;

  constructor(
    private authHttpService: AuthHttpService,
    private errorService: BalloonErrorService,
    private router: Router
  ) {
    this._user = this.getUserFromLs();
  }

  get user(): User | null {
    return this._user;
  }

  public login(authRequest: AuthRequest): void {
    this.authHttpService.getToken(authRequest).subscribe(
      response => this.onLogin(response),
      error => this.errorService.sendError(error['error']['message']));
  }

  public logout(): void {
    this._user = null;
    this.removeAuthLS();
  }

  private onLogin(response: AuthResponse) {
    this._user = this.converTokenToUser(response.token);
    this.setUserToLs(this._user);
    this.router.navigate(['/']);
  }

  private converTokenToUser(token: string): User {
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(token);
    const login: string = decodedToken['login'];
    const roles: Array<Roles> = decodedToken['roles'];
    return {token, login, roles};
  }

  private getUserFromLs(): User | null {
    const authFromLS = localStorage.getItem(AuthService.AUTH_PROP_NAME);
    if (authFromLS) {
      return JSON.parse(authFromLS) as User;
    }
    return null;
  }

  private setUserToLs(auth: User): void {
    localStorage.setItem(AuthService.AUTH_PROP_NAME, JSON.stringify(auth));
  }

  private removeAuthLS(): void {
    localStorage.removeItem(AuthService.AUTH_PROP_NAME);
  }
}
