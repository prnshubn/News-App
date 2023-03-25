import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './services/auth.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(public auth: AuthService) {}
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    console.log(request);
    request = request.clone({
      headers: request.headers
        .set('Authorization', 'Bearer ' + this.auth.getToken())
        .set('Access-Control-Allow-Origin', 'http://localhost:4200'),
    });
    return next.handle(request);
  }
}
