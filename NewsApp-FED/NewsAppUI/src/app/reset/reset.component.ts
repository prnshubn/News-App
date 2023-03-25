import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '../services/auth.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.css'],
})
export class ResetComponent {
  resetForm: FormGroup;

  name: FormControl;
  password: FormControl;

  constructor(private a: AuthService, private routerService: RouterService) {}

  ngOnInit() {
    (this.name = new FormControl('', [Validators.required])),
      (this.password = new FormControl('', [Validators.required])),
      (this.resetForm = new FormGroup({
        name: this.name,
        password: this.password,
      }));
  }

  onReset() {
    var email = this.a.userEmailId;
    var password = this.resetForm.get('password').value;
    var name = this.resetForm.get('name').value;

    var user = {
      email: email,
      password: password,
      name: name,
    };
    console.log(user);
    this.a.updatePassword(user);
    this.resetForm.reset();
  }

  onUserDelete(): void {
    this.a.userSelfDelete();
  }
}
