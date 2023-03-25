import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterService } from 'src/app/services/router.service';
import { NewsService } from '../../news.service';
import { ContainerComponent } from '../container/container.component';

@Component({
  selector: 'app-favorite',
  templateUrl: './favorite.component.html',
  styleUrls: ['./favorite.component.css'],
})
export class FavoriteComponent implements OnInit {
  newsList: Array<any> = [];
  favCheck = true;
  message: string;

  constructor(
    private newsService: NewsService,
    private routerSevice: RouterService,
    private snackBar: MatSnackBar
  ) {}
  ngOnInit() {
    this.getFavNews();
  }

  getFavNews() {
    this.newsService.getFavouriteNewsList().subscribe(
      (data) => {
        var temp = Object.values(data)[0];
        if (temp['length'] === 0) {
          this.snackBar.open('No favourite news found', 'OK', {
            duration: 2000,
          });
        }
        var dataList = Object.values(data)[0];
        this.newsList.push(dataList);
      },
      (error) => {
        console.log(error);
        if (error.status == 503) {
          console.log('hi');
          this.routerSevice.routeToException();
          const message = JSON.stringify(error.error.message);
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
}
