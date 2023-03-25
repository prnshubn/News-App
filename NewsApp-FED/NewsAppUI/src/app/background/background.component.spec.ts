import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { LoginComponent } from '../login/login.component';
import { RegisterComponent } from '../register/register.component';
import { ResetComponent } from '../reset/reset.component';

import { BackgroundComponent } from './background.component';

beforeEach(() =>
  TestBed.configureTestingModule({
    imports: [MatSnackBarModule, FormsModule, ReactiveFormsModule],
    providers: [BackgroundComponent, RegisterComponent],
  })
);

describe('BackgroundComponent', () => {
  let component: BackgroundComponent;
  let fixture: ComponentFixture<BackgroundComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        BackgroundComponent,
        LoginComponent,
        ResetComponent,
        RegisterComponent,
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(BackgroundComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show login form when loginCheck is true', () => {
    component.logIn.loginCheck = true;
    fixture.detectChanges();
    const loginForm = fixture.nativeElement.querySelector('app-login');
    expect(loginForm).toBeTruthy();
  });

  it('should show reset form when updateCheck is true', () => {
    component.updateCheck = true;
    fixture.detectChanges();
    const resetForm = fixture.nativeElement.querySelector('app-reset');
    expect(resetForm).toBeTruthy();
  });

  it('should show register form when clickCheck is true and updateCheck is false', () => {
    component.app.clickCheck = true;
    fixture.detectChanges();
    const registerForm = fixture.nativeElement.querySelector('app-register');
    expect(registerForm).toBeTruthy();
  });

  it('should show register form when signUpClickCheck is true and LinCheck is true', () => {
    component.signUpClickCheck = true;
    component.app.LinCheck = true;
    fixture.detectChanges();
    const registerForm = fixture.nativeElement.querySelector('app-register');
    expect(registerForm).toBeTruthy();
  });

  it('should call onSignUpClick when Sign Up link is clicked', () => {
    spyOn(component, 'onSignUpClick');
    const signUpLink = fixture.nativeElement.querySelector('#forget');
    signUpLink.click();
    expect(component.onSignUpClick).toHaveBeenCalled();
  });
});
