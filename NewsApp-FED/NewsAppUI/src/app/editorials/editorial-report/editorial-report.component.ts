import { Component } from '@angular/core';
import { RouterService } from 'src/app/services/router.service';
import { EditorialServiceService } from '../editorial-service.service';

@Component({
  selector: 'app-editorial-report',
  templateUrl: './editorial-report.component.html',
  styleUrls: ['./editorial-report.component.css'],
})
export class EditorialReportComponent {
  editorialReport: Array<any>;
  snackBar: any;
  likeStatus: string;

  constructor(private es: EditorialServiceService, private rs: RouterService) {
    this.editorialReport = [];
    this.likeStatus = '';
  }

  ngOnInit() {
    this.displayReport();
  }

  isLiked(editorial: any) {
    if (editorial.likeStatus) {
      this.likeStatus = 'Yes';
    } else {
      this.likeStatus = 'No';
    }
  }

  backClick() {
    this.rs.routeToEditorialsView();
  }
  displayReport() {
    this.es.getEditorialReport().subscribe(
      (data) => {
        console.log(data);
        const dataValues = Object.values(data);
        console.log(dataValues[0]);
        this.editorialReport.push(dataValues[0]);
      },
      (error) => {
        console.log(error);
        if (error.status === 503) {
          this.rs.routeToException();
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
