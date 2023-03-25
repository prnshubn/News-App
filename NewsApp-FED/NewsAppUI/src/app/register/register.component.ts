import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { AuthService } from '../services/auth.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  registrationForm: FormGroup;
  email: FormControl;
  name: FormControl;
  password: FormControl;
  public signUpSuccess: boolean = true;

  constructor(
    private a: AuthService,
    private routerService: RouterService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    (this.email = new FormControl('', [
      Validators.required,
      Validators.minLength(2),
    ])),
      (this.name = new FormControl('', [
        Validators.required,
        Validators.minLength(2),
      ])),
      (this.password = new FormControl('', [
        Validators.required,
        Validators.minLength(2),
      ])),
      (this.registrationForm = new FormGroup({
        email: this.email,
        name: this.name,
        password: this.password,
      }));
  }

  registerSubmit() {
    console.log(this.registrationForm.value);
    var email = this.registrationForm.get('email').value;
    var password = this.registrationForm.get('password').value;
    var name = this.registrationForm.get('name').value;
    var user = {
      email: email,
      password: password,
      name: name,
    };

    this.a.registerUser(user);
    this.signUpSuccess = true;

    this.registrationForm.reset();
  }
}
