import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { of, throwError } from 'rxjs';

import { AuthService } from './auth.service';
import { RouterService } from './router.service';

describe('AuthService', () => {
  let service: AuthService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuthService, RouterService],
    });

    service = TestBed.inject(AuthService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
    sessionStorage.clear();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('isAuthenticated', () => {
    it('should return true if token exists in sessionStorage', () => {
      sessionStorage.setItem('token', '1234');
      expect(service.isAuthenticated()).toBeTrue();
    });

    it('should return false if token does not exist in sessionStorage', () => {
      sessionStorage.removeItem('token');
      expect(service.isAuthenticated()).toBeFalse();
    });
  });

  it('should return true if token is in session storage', () => {
    spyOn(sessionStorage, 'getItem').and.returnValue('fakeToken');

    service.isUserAuthenticated().subscribe((isAuthenticated) => {
      expect(isAuthenticated).toBe(true);
    });
  });

  it('should return false if token is not in session storage', () => {
    spyOn(sessionStorage, 'getItem').and.returnValue(null);

    service.isUserAuthenticated().subscribe((isAuthenticated) => {
      expect(isAuthenticated).toBe(false);
    });
  });

  it('should return true if user role is admin', () => {
    spyOn(service, 'getRole').and.returnValue('admin');

    service.isUserAdmin().subscribe((isAdmin) => {
      expect(isAdmin).toBe(true);
    });
  });

  it('should return false if user role is not admin', () => {
    spyOn(service, 'getRole').and.returnValue('user');

    service.isUserAdmin().subscribe((isAdmin) => {
      expect(isAdmin).toBe(false);
    });
  });

  describe('getAuth', () => {
    it('should set token in sessionStorage and navigate to search when login is successful', () => {
      const email = 'test@example.com';
      const password = 'password';
      const response = { token: '1234' };

      spyOn(service.routerService, 'routeToSearch');

      service.getAuth(email, password);

      const req = httpTestingController.expectOne(
        'http://localhost:7070/authentication/user/login'
      );
      expect(req.request.method).toBe('POST');
      expect(req.request.body.get('email')).toEqual(email);
      expect(req.request.body.get('password')).toEqual(password);

      req.flush(response);

      expect(sessionStorage.getItem('token')).toEqual(response.token);
      expect(service.routerService.routeToSearch).toHaveBeenCalled();
    });
  });
});
