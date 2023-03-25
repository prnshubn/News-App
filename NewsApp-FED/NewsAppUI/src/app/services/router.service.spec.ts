import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';

import { RouterService } from './router.service';

describe('RouterService', () => {
  let service: RouterService;
  let routerSpy: jasmine.SpyObj<Router>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('Router', ['navigate']);
    TestBed.configureTestingModule({
      providers: [RouterService, { provide: Router, useValue: spy }],
    });

    service = TestBed.inject(RouterService);
    routerSpy = TestBed.inject(Router) as jasmine.SpyObj<Router>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should navigate to recommended', () => {
    service.routeToRecommended();
    expect(routerSpy.navigate).toHaveBeenCalledWith(['']);
  });

  it('should navigate to login', () => {
    service.routeToLogin();
    expect(routerSpy.navigate).toHaveBeenCalledWith(['background']);
  });

  it('should navigate to search', () => {
    service.routeToSearch();
    expect(routerSpy.navigate).toHaveBeenCalledWith(['news/top']);
  });

  it('should navigate to admin', () => {
    service.routeToAdmin();
    expect(routerSpy.navigate).toHaveBeenCalledWith(['home']);
  });

  it('should navigate to exception', () => {
    service.routeToException();
    expect(routerSpy.navigate).toHaveBeenCalledWith(['exception']);
  });
});
