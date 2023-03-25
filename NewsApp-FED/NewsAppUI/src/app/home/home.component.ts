import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EditorialServiceService } from '../editorials/editorial-service.service';
import { AuthService } from '../services/auth.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  userData: any;
  userMail: any;
  snackBar: any;
  constructor(
    private authService: AuthService,
    private _snackBar: MatSnackBar,
    private routerService: RouterService,
    private es: EditorialServiceService
  ) {}

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers() {
    this.authService.getAllUsers().subscribe(
      (res) => {
        console.log(res);
        this.userData = res;
      },
      (error) => {
        this.es.openSnackBar(JSON.stringify(error.error), 'OK');
        console.log(error);
        if (error.status === 503) {
          this.routerService.routeToException();

          this.snackBar.open(
            'User Service down. Please try again later!',
            'OK',
            {
              duration: 2000,
            }
          );
        }
      }
    );
  }

  deleteUser(row: any) {
    this.authService.deleteUser(row.email).subscribe(
      (res) => {
        this.getAllUsers();
        this.openSnackBar('User deleted successfully', 'OK');
      },
      (error) => {
        console.log(error);
        if (error.status === 503) {
          this.routerService.routeToException();

          this.snackBar.open(
            'User Service down. Please try again later!',
            'OK',
            {
              duration: 2000,
            }
          );
        }
      }
    );
  }

  clickDeactivate() {
    this.authService.deactivateUser(this.userMail).subscribe(
      () => {
        this.openSnackBar('User deactivated successfully', 'OK');
        this.getAllUsers();
      },
      (error) => {
        console.log(error);
        if (error.status === 503) {
          this.routerService.routeToException();

          this.snackBar.open(
            'User Service down. Please try again later!',
            'OK',
            {
              duration: 2000,
            }
          );
        }
      }
    );
  }

  checkUserStatus(row: any): boolean {
    if (JSON.stringify(row.active) == 'true') {
      return true;
    } else {
      return false;
    }
  }
  clickActivate(row: any) {
    this.authService.activateUser(row.email).subscribe(
      () => {
        this.openSnackBar('User activated', 'OK');
        this.getAllUsers();
      },
      (error) => {
        console.log(error);
        if (error.status === 503) {
          this.routerService.routeToException();
          this.snackBar.open(
            'User Service down. Please try again later!',
            'OK',
            {
              duration: 2000,
            }
          );
        }
      }
    );
  }
  setUserEmailOnClick(row) {
    this.userMail = row.email;
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 5000,
    });
  }
}
