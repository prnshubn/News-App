import { HttpRequest } from '@angular/common/http';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { TestBed, async } from '@angular/core/testing';
import { of } from 'rxjs';
import { AuthService } from './services/auth.service';

import { TokenInterceptor } from './token.interceptor';

describe('TokenInterceptor', () => {
  let interceptor: TokenInterceptor;
  let authService: AuthService;
  let httpMock: HttpTestingController;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TokenInterceptor, AuthService],
    });
    interceptor = TestBed.inject(TokenInterceptor);
    authService = TestBed.inject(AuthService);
    httpMock = TestBed.inject(HttpTestingController);
  }));

  it('should be created', () => {
    const interceptor: TokenInterceptor = TestBed.inject(TokenInterceptor);
    expect(interceptor).toBeTruthy();
  });

  it('should add an Authorization header with a token and Access-Control-Allow-Origin header to the request', () => {
    const mockRequest = new HttpRequest(
      'GET', // HTTP method
      '/api/data',
      {
        // Request headers (optional)
        Authorization: 'Bearer ' + authService.getToken(),
        'Access-Control-Allow-Origin': 'http://localhost:4200',
      }
    );
    spyOn(authService, 'getToken').and.returnValue('fake-token');
    interceptor.intercept(mockRequest, {
      handle: (req) => {
        expect(req.headers.get('Authorization')).toEqual('Bearer fake-token');
        expect(req.headers.get('Access-Control-Allow-Origin')).toEqual(
          'http://localhost:4200'
        );
        return of(null);
      },
    });
  });
});
