import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { catchError, map, Observable, of } from 'rxjs';
import { AuthService } from './services/auth.service';
import { RouterService } from './services/router.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private routerService: RouterService,
    private router: Router
  ) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this.authService.isUserAuthenticated().pipe(
      map((isAuthenticated) => {
        if (isAuthenticated) {
          return true;
        } else {
          this.routerService.routeToLogin();
          return false;
        }
      })
    );
  }
}
