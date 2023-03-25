import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { map, Observable } from 'rxjs';
import { User } from '../model/User';
import { RouterService } from './router.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  baseurl: string;
  loggedInUser: any;
  errMessage: string;
  flagSearch: true;
  role: boolean = false;

  userEmailId: string;

  durationInSeconds: number = 4;
  msg: string = '';

  constructor(
    private httpClient: HttpClient,
    public routerService: RouterService,
    private _snackBar: MatSnackBar
  ) {
    this.baseurl = 'http://localhost:7070/authentication/user';
    this.errMessage = '';
    this.userEmailId = '';
  }

  ngOnInit(): void {}

  isAuthenticated() {
    if (sessionStorage.getItem('token')) return true;
    else return false;
  }

  isUserAuthenticated() {
    return new Observable<boolean>((observer) => {
      if (sessionStorage.getItem('token')) {
        observer.next(true);
        observer.complete();
      } else {
        observer.next(false);
        observer.complete();
      }
    });
  }

  isUserAdmin2() {
    if (this.getRole() === 'admin') {
      return true;
    } else {
      return false;
    }
  }

  isUserAdmin() {
    return new Observable<boolean>((observer) => {
      if (this.getRole() === 'admin') {
        observer.next(true);
        observer.complete();
      } else {
        observer.next(false);
        observer.complete();
      }
    });
  }

  isUser() {
    return new Observable<boolean>((observer) => {
      if (this.getRole() === 'user') {
        observer.next(true);
        observer.complete();
      } else {
        observer.next(false);
        observer.complete();
      }
    });
  }

  getAuth(email, password) {
    const formData = new FormData();
    formData.append('email', email);
    formData.append('password', password);
    this.httpClient.post(`${this.baseurl}/login`, formData).subscribe(
      (data) => {
        console.log(data['role']);
        console.log(data, '>>>');
        sessionStorage.setItem('token', data['token']);
        sessionStorage.setItem('role', data['role']);
        sessionStorage.setItem('email', email);
        this.userEmailId = email;
        console.log(this.userEmailId);
        if (data['role'] === 'admin') {
          this.role = true;
          this.routerService.routeToAdmin();
        } else {
          this.routerService.routeToSearch();
        }
      },
      (error) => {
        this.errMessage = 'Wrong email & password combination';
        this.openSnackBar(this.errMessage, 'OK');
      }
    );
  }

  updatePassword(user) {
    console.log(user);
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    this.httpClient
      .put(`${this.baseurl}/updatePassword`, user, { headers })
      .subscribe(
        (data) => {
          console.log('successfully updated');
          this.logoutUser();
          this.routerService.routeToLogin();
        },
        (error) => {
          console.log(error);
        }
      );
  }

  registerUser(user) {
    console.log(user);
    this.httpClient
      .post(`${this.baseurl}/signup`, user, { responseType: 'text' })
      .subscribe(
        (data) => {
          this.openSnackBar('Signup successful', 'OK');
        },
        (error) => {
          this.openSnackBar('Something went wrong', 'OK');
        }
      );
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 5000,
    });
  }
  getToken() {
    return sessionStorage.getItem('token');
  }

  getRole() {
    return sessionStorage.getItem('role');
  }

  getStatus() {
    return sessionStorage.getItem('status');
  }

  logoutUser() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('role');
    this.routerService.routeToRecommended();
  }

  getUserData() {
    this.httpClient
      .get<User>(`${this.baseurl}findUserByEmail/${this.getToken()}`)
      .subscribe(
        (user) => {
          sessionStorage.setItem('name', user.name);
          sessionStorage.setItem('email', user.email);

          console.log(this.loggedInUser, 'loggedIn user');
        },
        (error) => console.log(error.message)
      );
  }

  public clear() {
    sessionStorage.clear();
  }

  //get all users

  getAllUsers() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    return this.httpClient
      .get<any>('http://localhost:7070/authentication/admin/getAllUsers', {
        headers,
      })
      .pipe(
        map((res: any) => {
          return res;
        })
      );
  }

  userSelfDelete() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    return this.httpClient
      .delete(`${this.baseurl}/delete/${this.userEmailId}`, {
        responseType: 'text',
        headers,
      })
      .subscribe(
        (data) => {
          console.log(data);
          this.logoutUser();
        },
        (error) => {
          console.log(error);
        }
      );
  }

  deleteUser(email: string) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    return this.httpClient
      .delete(
        'http://localhost:7070/authentication/admin/deleteUser/' + email,
        { responseType: 'text', headers }
      )
      .pipe(
        map((res: any) => {
          return res;
        })
      );
  }

  deactivateUser(email: any): Observable<any> {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );

    return this.httpClient.put<any>(
      'http://localhost:7070/authentication/admin/deactivateUser/' + email,
      '',
      {
        headers,
      }
    );
  }

  activateUser(email: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );

    return this.httpClient.put(
      'http://localhost:7070/authentication/admin/activateUser/' + email,
      '',
      { headers }
    );
  }
}
