import { Component, Injectable } from '@angular/core';
import { AppComponent } from '../app.component';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-background',
  templateUrl: './background.component.html',
  styleUrls: ['./background.component.css'],
})
@Injectable()
export class BackgroundComponent {
  public signUpClickCheck: boolean = false;
  public updateCheck: boolean = false;

  constructor(
    public register: RegisterComponent,
    public logIn: LoginComponent,
    public app: AppComponent
  ) {}

  ngOnInit() {
    this.logIn.loginCheck = true;
    this.signUpClickCheck = false;
    console.log('check = ' + this.register.signUpSuccess);
  }

  onSignUpClick(): void {
    this.signUpClickCheck = true;
    this.logIn.loginCheck = false;
    this.app.btnSwap = true;
    this.updateCheck = false;
    this.app.clickCheck = true;
  }

  clickLogIn(): void {
    this.logIn.loginCheck = true;
    this.app.clickCheck = false;
    this.signUpClickCheck = false;
    this.app.btnSwap = false;
    this.updateCheck = false;
  }

  clickUpdate(): void {
    this.updateCheck = true;
    this.app.clickCheck = true;
    this.logIn.loginCheck = false;
    this.app.isResetActive = true;
  }
}
