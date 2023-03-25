import { Component, OnInit } from '@angular/core';
import { NewsService } from '../../news.service';

@Component({
  selector: 'app-topnews',
  templateUrl: './topnews.component.html',
  styleUrls: ['./topnews.component.css'],
})
export class TopnewsComponent implements OnInit {
  newsList: Array<any>;
  routerService: any;
  snackBar: any;
  constructor(private newsService: NewsService) {
    this.newsList = [];
  }
  ngOnInit() {
    this.newsService.getTopNewsList().subscribe(
      (data) => {
        console.log(data);
        this.newsList.push(...Object.values(data)[0]);
        console.log(this.newsList);
      },
      (error) => {
        console.log(error);
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
      }
    );
  }
}
