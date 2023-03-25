import { TestBed } from '@angular/core/testing';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { of } from 'rxjs';

import { AdminGuard } from './admin.guard';
import { AuthService } from './services/auth.service';
import { RouterService } from './services/router.service';

describe('AdminGuard', () => {
  let guard: AdminGuard;
  let authService: jasmine.SpyObj<AuthService>;
  let routerService: jasmine.SpyObj<RouterService>;
  const routeSnapshot: ActivatedRouteSnapshot = {} as ActivatedRouteSnapshot;
  const stateSnapshot: RouterStateSnapshot = {} as RouterStateSnapshot;

  beforeEach(() => {
    const authSpy = jasmine.createSpyObj('AuthService', ['isUserAdmin']);
    const routerSpy = jasmine.createSpyObj('RouterService', [
      'routeToException',
    ]);
    TestBed.configureTestingModule({
      providers: [
        AdminGuard,
        { provide: AuthService, useValue: authSpy },
        { provide: RouterService, useValue: routerSpy },
      ],
    });
    guard = TestBed.inject(AdminGuard);
    authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
    routerService = TestBed.inject(
      RouterService
    ) as jasmine.SpyObj<RouterService>;
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  describe('canActivate', () => {
    let route: ActivatedRouteSnapshot;
    let state: RouterStateSnapshot;

    beforeEach(() => {
      route = {} as ActivatedRouteSnapshot;
      state = {} as RouterStateSnapshot;
    });

    it('should return true when user is an admin', () => {
      authService.isUserAdmin.and.returnValue(of(true));

      guard.canActivate(routeSnapshot, stateSnapshot).subscribe((result) => {
        expect(result).toBeTrue();
        expect(authService.isUserAdmin).toHaveBeenCalled();
        expect(routerService.routeToException).not.toHaveBeenCalled();
      });
    });

    it('should return false and route to exception page when user is not an admin', () => {
      authService.isUserAdmin.and.returnValue(of(false));

      guard.canActivate(routeSnapshot, stateSnapshot).subscribe((result) => {
        expect(result).toBeFalse();
        expect(authService.isUserAdmin).toHaveBeenCalled();
        expect(routerService.routeToException).toHaveBeenCalled();
      });
    });
  });
});
