import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule],
      declarations: [LoginComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize the form with empty fields', () => {
    expect(component.loginform.value).toEqual({ email: '', password: '' });
  });

  it('should validate email field', () => {
    const email = component.email;
    expect(email.valid).toBeFalsy();
  });

  it('should validate password field', () => {
    const password = component.password;
    expect(password.valid).toBeFalsy();
  });

  it('should call loginSubmit method when the form is submitted', () => {
    spyOn(component, 'loginSubmit');
    const form = fixture.nativeElement.querySelector('form');
    form.dispatchEvent(new Event('ngSubmit'));
    expect(component.loginSubmit).toHaveBeenCalled();
  });
});
