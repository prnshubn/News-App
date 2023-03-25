import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginform: FormGroup;
  password: FormControl;
  email: FormControl;
  submitMessage: string;

  public loginCheck: boolean = false;
  private _snackBar: any;
  constructor(private a: AuthService, private routerService: RouterService) {}
  ngOnInit() {
    (this.password = new FormControl('', [
      Validators.required,
      Validators.minLength(2),
    ])),
      (this.email = new FormControl('', [
        Validators.required,
        Validators.minLength(2),
      ])),
      (this.loginform = new FormGroup({
        password: this.password,
        email: this.email,
      }));
  }

  loginSubmit() {
    console.log(this.loginform.value);
    var email = this.loginform.get('email').value;
    var password = this.loginform.get('password').value;
    this.a.getAuth(email, password);
    this.loginform.reset();
  }
}
