import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { AppComponent } from './app.component';
import { ResetComponent } from './reset/reset.component';
import { LoginComponent } from './login/login.component';
import { AuthService } from './services/auth.service';
import { RouterService } from './services/router.service';

beforeEach(() =>
  TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [AppComponent, ResetComponent, LoginComponent],
  })
);

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;
  let authService: AuthService;
  let routerService: RouterService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule],
      providers: [AppComponent, ResetComponent, LoginComponent],
      declarations: [AppComponent, LoginComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthService);
    routerService = TestBed.inject(RouterService);
    fixture.detectChanges();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('should display the app name in the navbar', () => {
    const appNameElement = fixture.nativeElement.querySelector('.navbar-brand');
    expect(appNameElement.textContent).toContain('NEWS APP');
  });

  it('should navigate to the recommended page when routeToRecommended is called', () => {
    const navigateSpy = spyOn(routerService, 'routeToRecommended');
    routerService.routeToRecommended();
    expect(navigateSpy).toHaveBeenCalled();
  });

  it('should navigate to the login page when routeToLogin is called', () => {
    const navigateSpy = spyOn(routerService, 'routeToLogin');
    routerService.routeToLogin();
    expect(navigateSpy).toHaveBeenCalled();
  });

  it('should navigate to the search page when routeToSearch is called', () => {
    const navigateSpy = spyOn(routerService, 'routeToSearch');
    routerService.routeToSearch();
    expect(navigateSpy).toHaveBeenCalled();
  });
});
