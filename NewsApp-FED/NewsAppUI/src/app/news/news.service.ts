import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { RouterService } from '../services/router.service';

@Injectable({
  providedIn: 'root',
})
export class NewsService {
  springEndpoint: string;
  newsappServiceURL: string;
  callback: (news) => Observable<any>;

  constructor(
    private http: HttpClient,
    private a: AuthService,
    private snackBar: MatSnackBar,
    private routerService: RouterService
  ) {
    this.springEndpoint = 'http://localhost:7070/user/interaction/';
    this.newsappServiceURL = 'http://localhost:7070/newsapp/service';
  }

  addToFavouriteList(news: any) {
    news.email = this.a.userEmailId;
    var type = news.newsType;
    console.log(type);
    switch (type) {
      case 'top': {
        this.callback = this.addTopNews;
        this.addNewsBasedOnType(news);
        break;
      }

      case 'sports': {
        this.callback = this.addSportsNews;
        this.addNewsBasedOnType(news);
        break;
      }

      case 'business': {
        this.callback = this.addBusinessNews;
        this.addNewsBasedOnType(news);
        break;
      }
    }
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

  addNewsBasedOnType(newsObj: any) {
    this.callback(newsObj).subscribe(
      () => {
        this.openSnackBar(newsObj.title + ' added to favorites', 'OK');
      },
      (error) => {
        console.log(error);
        if (error.status === 503) {
          this.routerService.routeToException();
          this.snackBar.open(JSON.stringify(error['error']), 'OK', {
            duration: 2000,
          });
        } else {
          this.snackBar.open('Something went wrong', 'OK', {
            duration: 2000,
          });
        }
      }
    );
  }
  addTopNews(news): Observable<any> {
    console.log(news);
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + sessionStorage.getItem('token')
    );
    return this.http.post(`${this.newsappServiceURL}/addNews`, news, {
      headers,
    });
  }

  addSportsNews(news): Observable<any> {
    console.log(news);
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + sessionStorage.getItem('token')
    );
    return this.http.post(`${this.newsappServiceURL}/addSportsNews`, news, {
      headers,
    });
  }

  addBusinessNews(news): Observable<any> {
    console.log(news);
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + sessionStorage.getItem('token')
    );
    return this.http.post(`${this.newsappServiceURL}/addBusinessNews`, news, {
      headers,
    });
  }

  getToken() {
    return sessionStorage.getItem('token');
  }

  getFavouriteNewsList(): Observable<any> {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    return this.http.get(
      this.newsappServiceURL + '/getFavouriteNews/' + this.a.userEmailId,
      {
        headers,
      }
    );
  }

  getFavouriteEditorialList(): Observable<any> {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    return this.http.get(
      this.newsappServiceURL + '/getFavouriteEditorials/' + this.a.userEmailId,
      {
        headers,
      }
    );
  }

  deleteFromFavouriteList(news) {
    console.log(news['title']);
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    return this.http.delete(
      `${this.newsappServiceURL}/delete/${this.a.userEmailId}/${news.title}`,
      { headers }
    );
  }

  getTopNewsList(): Observable<Array<any>> {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    return this.http.get<Array<any>>(`${this.springEndpoint}getAllNews`, {
      headers,
    });
  }

  headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.getToken());

  getSportsNews() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    return this.http.get<Array<any>>(`${this.springEndpoint}getAllSportsNews`, {
      headers,
    });
  }

  getBusinessNews() {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );
    return this.http.get<Array<any>>(
      `${this.springEndpoint}getAllBusinessNews`,
      {
        headers,
      }
    );
  }

  searchNews(query: string) {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + this.getToken()
    );

    return this.http.get<Array<any>>(
      `${this.springEndpoint}getAllSearchedNews/${query}`,
      { headers }
    );
  }
}
