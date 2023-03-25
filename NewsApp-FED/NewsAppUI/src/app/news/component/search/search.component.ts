import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { EditorialServiceService } from 'src/app/editorials/editorial-service.service';
import { RouterService } from 'src/app/services/router.service';
import { NewsService } from '../../news.service';
import { ThumbnailComponent } from '../thumbnail/thumbnail.component';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
  newsList: Array<any>;
  searchform: FormGroup;
  keyword: FormControl;
  snackBar: any;
  constructor(
    private newsService: NewsService,
    private routerSevice: RouterService,
    private editorialService: EditorialServiceService
  ) {}

  ngOnInit() {
    (this.keyword = new FormControl('')),
      (this.searchform = new FormGroup({
        keyword: this.keyword,
      }));
  }

  searchSubmit() {
    console.log(this.searchform.value);
    var searchKey = this.searchform.get('keyword').value;
    console.log(searchKey);
    // this.thumbnail.isSearch = true;
    this.newsService.searchNews(searchKey).subscribe(
      (data) => {
        this.newsList = Object.values(data)[0];
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
