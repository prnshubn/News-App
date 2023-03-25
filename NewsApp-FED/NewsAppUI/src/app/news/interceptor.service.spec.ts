import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { AuthService } from '../services/auth.service';

import { InterceptorService } from './interceptor.service';

describe('InterceptorService', () => {
  let service: InterceptorService;
  let authServiceSpy: jasmine.SpyObj<AuthService>;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('AuthService', ['getToken']);
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [InterceptorService, { provide: AuthService, useValue: spy }],
    });
    service = TestBed.inject(InterceptorService);
    authServiceSpy = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
