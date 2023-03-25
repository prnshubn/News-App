import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ResetComponent } from './reset/reset.component';
import { AuthService } from './services/auth.service';
import { RouterService } from './services/router.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'login';

  public updateForm: FormGroup;
  public clickCheck: boolean = false;
  public LinCheck: boolean = true;
  public isResetActive: boolean = false;
  public btnSwap: boolean = false;
  public adminCheck: boolean = false;
  public admin_identify: boolean = true;
  constructor(
    private authService: AuthService,
    private router: Router,
    private reset: ResetComponent,
    private rs: RouterService,
    public login: LoginComponent
  ) {}

  ngOnInit(): void {}
  public isLoggedIn() {
    return this.authService.isAuthenticated();
  }

  public admin_identify_func() {
    return this.authService.isUserAdmin2();
  }

  public logout() {
    this.authService.clear();
    this.authService.logoutUser();
    this.router.navigate(['/background']);
    this.clickCheck = false;
  }

  public loginClick() {
    this.btnSwap = false;
    this.clickCheck = false;
    this.LinCheck = false;
    this.router.navigate(['/background']);
  }
  clickBtn(): void {
    this.btnSwap = true;
    this.clickCheck = true;
    this.login.loginCheck = false;
    console.log('signup check = ' + this.clickCheck);
  }

  adminClick(): void {
    this.adminCheck = true;
    this.rs.routeToAdmin();
  }
}
