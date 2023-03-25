import { Component, OnInit } from '@angular/core';
import { EditorialContainerComponent } from 'src/app/editorials/editorial-container/editorial-container.component';
import { EditorialServiceService } from 'src/app/editorials/editorial-service.service';
import { RouterService } from 'src/app/services/router.service';
import { NewsService } from '../../news.service';

@Component({
  selector: 'app-sports-news',
  templateUrl: './sports-news.component.html',
  styleUrls: ['./sports-news.component.css'],
})
export class SportsNewsComponent implements OnInit {
  sportsNewsList: Array<any>;
  snackBar: any;
  constructor(
    private newsService: NewsService,
    private routerSevice: RouterService,
    private editorialService: EditorialServiceService
  ) {
    this.sportsNewsList = [];
  }

  ngOnInit() {
    this.newsService.getSportsNews().subscribe(
      (data) => {
        this.sportsNewsList.push(...Object.values(data)[0]);
      },
      (error) => {
        console.log(error);
        this.editorialService.openSnackBar(JSON.stringify(error.error), 'OK');
        console.log(error);
        if (error.status === 503) {
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
