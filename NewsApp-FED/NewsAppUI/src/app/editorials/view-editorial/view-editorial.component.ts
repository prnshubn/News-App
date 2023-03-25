import { Component } from '@angular/core';
import { RouterService } from 'src/app/services/router.service';
import { EditorialServiceService } from '../editorial-service.service';

@Component({
  selector: 'app-view-editorial',
  templateUrl: './view-editorial.component.html',
  styleUrls: ['./view-editorial.component.css'],
})
export class ViewEditorialComponent {
  editorial: Array<any>;
  favCheck = false;
  snackBar: any;

  constructor(
    private es: EditorialServiceService,
    private routerSevice: RouterService
  ) {
    this.editorial = [];
  }

  ngOnInit() {
    this.viewEditorials();
  }

  viewEditorials() {
    this.es.getEditorial().subscribe(
      (data) => {
        const dataValues = Object.values(data);
        console.log(...dataValues);
        this.editorial.push(...dataValues[0]);
      },
      (error) => {
        this.es.openSnackBar(JSON.stringify(error.error), 'OK');
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
