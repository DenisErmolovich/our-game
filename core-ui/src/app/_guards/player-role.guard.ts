import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from '../_services/auth/auth.service';
import {Roles} from '../_enums/roles.enum';
import {User} from '../_models/user';

@Injectable({
  providedIn: 'root'
})
export class PlayerRoleGuard implements CanActivate {
  private role: Roles = Roles.PLAYER;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.hasRole(this.authService.user, this.role) ? true : this.router.parseUrl('/login');
  }

  private hasRole(user: User, role: Roles): boolean {
    return this.hasRoles(user) && user.roles.includes(role);
  }

  private hasRoles(user: User): boolean {
    return !!user && !!user.roles;
  }
}
