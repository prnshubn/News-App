import { TestBed } from '@angular/core/testing';
import {
  ActivatedRouteSnapshot,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { of, throwError } from 'rxjs';

import { AuthGuard } from './auth.guard';
import { AuthService } from './services/auth.service';
import { RouterService } from './services/router.service';

describe('AuthGuard', () => {
  let guard: AuthGuard;
  let authService: jasmine.SpyObj<AuthService>;
  let routerService: jasmine.SpyObj<RouterService>;
  let router: jasmine.SpyObj<Router>;
  const routeSnapshot: ActivatedRouteSnapshot = {} as ActivatedRouteSnapshot;
  const stateSnapshot: RouterStateSnapshot = {} as RouterStateSnapshot;

  beforeEach(() => {
    const authSpy = jasmine.createSpyObj('AuthService', [
      'isUserAuthenticated',
    ]);
    const routerSpy = jasmine.createSpyObj('RouterService', ['routeToLogin']);
    const routerObj = jasmine.createSpyObj('Router', ['navigate']);

    TestBed.configureTestingModule({
      providers: [
        AuthGuard,
        { provide: AuthService, useValue: authSpy },
        { provide: RouterService, useValue: routerSpy },
        { provide: Router, useValue: routerObj },
      ],
    });

    guard = TestBed.inject(AuthGuard);
    authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
    routerService = TestBed.inject(
      RouterService
    ) as jasmine.SpyObj<RouterService>;
    router = TestBed.inject(Router) as jasmine.SpyObj<Router>;
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('should return true when user is authenticated', () => {
    authService.isUserAuthenticated.and.returnValue(of(true));

    guard.canActivate(routeSnapshot, stateSnapshot).subscribe((result) => {
      expect(result).toBeTrue();
      expect(authService.isUserAuthenticated).toHaveBeenCalled();
      expect(routerService.routeToLogin).not.toHaveBeenCalled();
      expect(router.navigate).not.toHaveBeenCalled();
    });
  });

  it('should return false and route to login page when user is not authenticated', () => {
    authService.isUserAuthenticated.and.returnValue(of(false));

    guard.canActivate(routeSnapshot, stateSnapshot).subscribe((result) => {
      expect(result).toBeFalse();
      expect(authService.isUserAuthenticated).toHaveBeenCalled();
      expect(routerService.routeToLogin).toHaveBeenCalled();
    });
  });
});
