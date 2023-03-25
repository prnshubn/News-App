import { Component, Input } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NewsService } from 'src/app/news/news.service';
import { RouterService } from 'src/app/services/router.service';
import { EditorialServiceService } from '../editorial-service.service';

@Component({
  selector: 'app-editorial-container',
  templateUrl: './editorial-container.component.html',
  styleUrls: ['./editorial-container.component.css'],
})
export class EditorialContainerComponent {
  @Input()
  editorialList: Array<any>;

  @Input()
  favCheck: boolean;

  constructor(
    private routerService: RouterService,
    private es: EditorialServiceService,
    private ns: NewsService,
    private snackBar: MatSnackBar
  ) {}
  headingNewsClick() {
    this.routerService.routeToFavNews();
  }

  deleteEdiFromFavList(editorial: any) {
    console.log(editorial);
    this.es.deleteFavEditorial(editorial).subscribe(
      (data) => {
        this.snackBar.open(editorial.title + ' deleted successfully', 'OK');
      },
      (error) => {
        console.log(error);

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
    for (var i = 0; i < this.editorialList.length; i++) {
      if (this.editorialList[i].title === editorial.title) {
        this.editorialList.splice(i, 1);
      }
    }
  }
}
