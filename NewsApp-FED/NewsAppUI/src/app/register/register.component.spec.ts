import { HttpClientTestingModule } from '@angular/common/http/testing';
import {
  async,
  ComponentFixture,
  inject,
  TestBed,
} from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

import { RegisterComponent } from './register.component';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let myService;
  let mySpy;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule, MatSnackBarModule],
      declarations: [RegisterComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a valid form when email, name, and password are provided', () => {
    component.registrationForm.controls['email'].setValue('test@test.com');
    component.registrationForm.controls['name'].setValue('Test User');
    component.registrationForm.controls['password'].setValue('password');
    expect(component.registrationForm.valid).toBeTruthy();
  });

  it('should have an invalid form when email is empty', () => {
    component.registrationForm.controls['name'].setValue('Test User');
    component.registrationForm.controls['password'].setValue('password');
    expect(component.registrationForm.valid).toBeFalsy();
  });

  it('should have an invalid form when name is empty', () => {
    component.registrationForm.controls['email'].setValue('test@test.com');
    component.registrationForm.controls['password'].setValue('password');
    expect(component.registrationForm.valid).toBeFalsy();
  });

  it('should have an invalid form when password is empty', () => {
    component.registrationForm.controls['email'].setValue('test@test.com');
    component.registrationForm.controls['name'].setValue('Test User');
    expect(component.registrationForm.valid).toBeFalsy();
  });

  it('should submit Registration Form', () => {
    spyOn(component, 'registerSubmit');
    const form = component.registrationForm;
    form.controls['email'].setValue('test@test.com');
    form.controls['name'].setValue('John Doe');
    form.controls['password'].setValue('password');
    fixture.detectChanges();
    const button = fixture.nativeElement.querySelector('button');
    button.click();
    fixture.detectChanges();
    expect(component.registerSubmit).toHaveBeenCalled();
  });
});
