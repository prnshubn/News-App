import { OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { RouterService } from 'src/app/services/router.service';
import { NewsService } from '../../news.service';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css'],
})
export class ContainerComponent implements OnInit {
  @Input()
  newsList: Array<any>;

  @Input()
  favCheck: boolean;

  favSwap: boolean;

  constructor(
    private newsService: NewsService,
    private snackBar: MatSnackBar,
    private a: AuthService,
    private routerService: RouterService
  ) {
    this.newsList = [];
  }

  ngOnInit() {
    this.favSwap = false;
  }

  headingNewsClick() {
    this.routerService.routeToFavEditorial();
  }

  headingEditorialClick() {
    this.routerService.routeToFavNews();
  }

  addNewsToFavList(news) {
    this.newsService.addToFavouriteList(news);
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

  deleteNews(news) {
    this.newsService.deleteFromFavouriteList(news).subscribe(
      (data) => {
        this.openSnackBar(news.title + ' has been removed', 'OK');
      },
      (error) => {
        if (error.status === 503) {
          this.routerService.routeToException();
          const message = JSON.stringify(error.error.message);
          this.snackBar.open(
            'User Service down. Please try again later!',
            'OK',
            {
              duration: 2000,
            }
          );
        }
        console.log(error);
      }
    );
    for (var i = 0; i < this.newsList.length; i++) {
      if (this.newsList[i].title === news.title) {
        this.newsList.splice(i, 1);
      }
    }
  }
}
