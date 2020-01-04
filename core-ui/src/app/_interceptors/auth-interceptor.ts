import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from '../_services/auth/auth.service';
import {User} from '../_models/user';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private authService: AuthService
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const headers = this.addAuthHeader(req.headers, this.authService.user);
    const newReq = req.clone({headers});
    return next.handle(newReq);
  }

  private addAuthHeader(headers: HttpHeaders, user: User): HttpHeaders {
    const token = this.getToken(user);
    if (token) {
      headers = headers.append('Authorization', `Bearer ${token}`);
    }
    return headers;
  }

  private getToken(user: User): string | null {
    return user && user.token ? user.token : null;
  }
}
