import { EventEmitter } from '@angular/core';
import { Output } from '@angular/core';
import { Component, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { RouterService } from 'src/app/services/router.service';
import { EditorialServiceService } from '../editorial-service.service';

@Component({
  selector: 'app-editorial-thumbnail',
  templateUrl: './editorial-thumbnail.component.html',
  styleUrls: ['./editorial-thumbnail.component.css'],
})
export class EditorialThumbnailComponent {
  @Input()
  editorial: any;

  @Input()
  favCheck: boolean = false;

  @Output()
  deleteEditorial = new EventEmitter();

  likeClickCheck: boolean;
  reportClickCheck: boolean;
  favedcheck: boolean;
  reportForm: FormGroup;
  report: FormControl;

  constructor(
    private eservice: EditorialServiceService,
    private a: AuthService,
    private rs: RouterService
  ) {
    this.likeClickCheck = false;
    this.reportClickCheck = false;
    this.favedcheck = false;
  }

  ngOnInit() {
    (this.report = new FormControl()),
      (this.reportForm = new FormGroup({
        userReport: this.report,
      }));
  }

  onDelete() {
    this.eservice.deleteEditorial();
  }

  setEditorialTitle() {
    console.log(this.editorial.title);
    this.eservice.newsTitle = this.editorial.title;
  }

  adminCheck(): boolean {
    return this.a.isUserAdmin2();
  }

  like_fun() {
    this.likeClickCheck = !this.likeClickCheck;
    console.log(this.a.userEmailId);
    this.eservice.likeEditorial({
      email: this.a.userEmailId,
      ediTitle: this.editorial.title,
    });
  }

  dislike_fun() {
    this.likeClickCheck = !this.likeClickCheck;
    this.eservice.dislikeEditorial({
      email: this.a.userEmailId,
      ediTitle: this.editorial.title,
    });
  }

  report_fun() {
    this.reportClickCheck = !this.reportClickCheck;
    console.log(this.reportClickCheck);
  }

  postComment() {
    var comment = this.reportForm.get('userReport').value;
    console.log(comment);

    this.eservice.addReport({
      email: this.a.userEmailId,
      ediTitle: this.editorial.title,
      report: comment,
    });

    this.reportForm.reset();
    this.reportClickCheck = false;
  }

  viewReport() {
    this.setEditorialTitle();
    this.rs.routeToReports();
  }

  editorialFav() {
    this.eservice.favEditorial({
      email: sessionStorage.getItem('email'),
      title: this.editorial.title,
      contentType: 'editorial',
      isNews: 'editorial',
    });
  }

  favEditorialDelete() {
    this.deleteEditorial.emit(this.editorial);
  }
}
