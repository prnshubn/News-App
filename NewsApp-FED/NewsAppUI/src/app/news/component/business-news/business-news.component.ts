import { Component, OnInit } from '@angular/core';
import { RouterService } from 'src/app/services/router.service';
import { NewsService } from '../../news.service';

@Component({
  selector: 'app-business-news',
  templateUrl: './business-news.component.html',
  styleUrls: ['./business-news.component.css'],
})
export class BusinessNewsComponent implements OnInit {
  businessNewsList: Array<any>;
  snackBar: any;
  constructor(
    private newsService: NewsService,
    private routerService: RouterService
  ) {
    this.businessNewsList = [];
  }

  ngOnInit() {
    this.newsService.getBusinessNews().subscribe(
      (data) => {
        this.businessNewsList.push(...Object.values(data)[0]);
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
