import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NewsService } from '../../news/news.service';
import { ContainerComponent } from '../../news/component/container/container.component';
import { RouterService } from 'src/app/services/router.service';

@Component({
  selector: 'app-favorite-editorials',
  templateUrl: './favorite-editorials.component.html',
  styleUrls: ['./favorite-editorials.component.css'],
})
export class FavoriteEditorialsComponent {
  editorialList: Array<any> = [];
  favCheck = true;

  constructor(
    private newsService: NewsService,
    private routerSevice: RouterService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.getFavEditorials();
  }

  getFavEditorials() {
    this.newsService.getFavouriteEditorialList().subscribe(
      (data) => {
        var temp = Object.values(data)[0];
        if (temp['length'] === 0) {
          this.snackBar.open('No favourite editorial found', 'OK', {
            duration: 2000,
          });
        }
        this.editorialList.push(Object.values(data)[0]);
      },
      (error) => {
        console.log(error);
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
