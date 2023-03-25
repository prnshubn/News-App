import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { map, Observable } from 'rxjs';
import { AuthService } from './services/auth.service';
import { RouterService } from './services/router.service';

@Injectable({
  providedIn: 'root',
})
export class UserGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private routerService: RouterService
  ) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {
    return this.authService.isUser().pipe(
      map((isUser) => {
        if (isUser) {
          return true;
        } else {
          this.routerService.routeToException();
          return false;
        }
      })
    );
  }
}
