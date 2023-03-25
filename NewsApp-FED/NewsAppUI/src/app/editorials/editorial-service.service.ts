import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { RouterService } from '../services/router.service';

@Injectable({
  providedIn: 'root',
})
export class EditorialServiceService {
  newsTitle: string;
  springEndpoint: string;
  interactionURL: string;
  springEndpoint2: string;
  newsappServiceURL: string;
  constructor(
    private a: AuthService,
    private httpClient: HttpClient,
    private _snackBar: MatSnackBar,
    private rs: RouterService
  ) {
    this.newsTitle = '';
    this.springEndpoint = 'http://localhost:7070/admin/editorial';
    this.interactionURL = 'http://localhost:7070/user/interaction';
    this.springEndpoint2 = 'http://localhost:7070/user/feeder';
    this.newsappServiceURL = 'http://localhost:7070/newsapp/service';
  }
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 5000,
    });
  }

  saveEditorial(editorial: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.a.getToken()
    );
    console.log(editorial);
    this.httpClient
      .post(`${this.springEndpoint}/addEditorial`, editorial, {
        headers,
      })
      .subscribe(
        (data) => {
          this.openSnackBar('Editorial published!', 'OK');
        },
        (error) => {
          this.openSnackBar('Something went wrong', 'OK');
        }
      );
  }
  getEditorial(): Observable<Array<any>> {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.a.getToken()
    );

    return this.httpClient.get<Array<any>>(
      `${this.springEndpoint}/getAllEditorials`,
      {
        headers,
      }
    );
  }

  deleteEditorial() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.a.getToken()
    );
    this.httpClient
      .delete(`${this.springEndpoint}/delete/editorial/${this.newsTitle}`, {
        headers,
        responseType: 'text',
      })
      .subscribe(
        (data) => {
          this.openSnackBar('Editorial deleted', 'OK');
          this.rs.routeToEditorials();
        },
        (error) => {
          this.openSnackBar(error.error, 'OK');

          console.log(error);
        }
      );
  }

  likeEditorial(editorial: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.a.getToken()
    );

    this.httpClient
      .post(`${this.interactionURL}/addLike`, editorial, {
        headers,
      })
      .subscribe(
        (data) => {
          this.openSnackBar('Post Liked', 'OK');
        },
        (error) => {
          this.openSnackBar('Something went wrong', 'OK');
          console.log(error);
        }
      );
  }

  dislikeEditorial(editorial: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.a.getToken()
    );

    this.httpClient
      .post(`${this.interactionURL}/deleteLike`, editorial, {
        headers,
      })
      .subscribe(
        (data) => {
          this.openSnackBar('Like removed', 'OK');
        },
        (error) => {
          this.openSnackBar('Something went wrong', 'OK');
          console.log(error);
        }
      );
  }

  addReport(editorial: any) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.a.getToken()
    );
    this.httpClient
      .post(`${this.interactionURL}/addReport`, editorial, {
        headers,
      })
      .subscribe(
        (data) => {
          this.openSnackBar('report added', 'OK');
        },
        (error) => {
          this.openSnackBar('Something went wrong', 'OK');
          console.log(error);
        }
      );
  }

  getEditorialReport(): Observable<any> {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.a.getToken()
    );
    return this.httpClient.get(
      `${this.springEndpoint}/report/${this.newsTitle}`,
      { headers }
    );
  }

  favEditorial(rr: any) {
    console.log(rr);
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.a.getToken()
    );

    this.httpClient
      .post(`${this.springEndpoint2}/addFavourites`, rr, {
        headers,
      })
      .subscribe(
        (data) => {
          this.openSnackBar(rr.title + ' added to Fav List', 'OK');
        },
        (error) => {
          this.openSnackBar('Something went wrong', 'OK');
        }
      );
  }

  deleteFavEditorial(editorial) {
    console.log(editorial['title']);
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.a.getToken()
    );
    return this.httpClient.delete(
      `${this.newsappServiceURL}/delete/${this.a.userEmailId}/${editorial.title}`,
      { headers }
    );
  }
}
