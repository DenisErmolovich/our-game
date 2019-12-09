import {Injectable} from '@angular/core';
import {AuthResponse} from "../../_models/auth-response";
import {AuthHttpService} from "./auth-http.service";
import {AuthRequest} from "../../_models/auth-request";
import {BalloonErrorService} from "../errors/balloon-error.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private static readonly AUTH_PROP_NAME = 'auth';
  private _auth: AuthResponse;

  constructor(
    private authHttpService: AuthHttpService,
    private errorService: BalloonErrorService,
    private router: Router
  ) {
    this._auth = this.getAuthLS();
  }


  get auth(): AuthResponse {
    return this._auth;
  }

  public login(authRequest: AuthRequest): void {
    this.authHttpService.getToken(authRequest).subscribe(
      response => this.onLogin(response),
      error => this.errorService.sendError(error['error']['message']));
  }

  public logout(): void {
    this._auth = null;
    this.removeAuthLS();
  }

  private onLogin(response: AuthResponse) {
    this._auth = response;
    this.setAuthLS(response);
    this.router.navigate(['/']);
  }

  private getAuthLS(): AuthResponse | null {
    const authFromLS = localStorage.getItem(AuthService.AUTH_PROP_NAME);
    if (authFromLS) {
      return authFromLS as AuthResponse;
    }
    return null;
  }

  private setAuthLS(auth: AuthResponse): void {
    localStorage.setItem(AuthService.AUTH_PROP_NAME, JSON.stringify(auth));
  }

  private removeAuthLS(): void {
    localStorage.removeItem(AuthService.AUTH_PROP_NAME);
  }
}
