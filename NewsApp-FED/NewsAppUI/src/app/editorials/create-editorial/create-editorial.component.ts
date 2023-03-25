import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { EditorialServiceService } from '../editorial-service.service';

@Component({
  selector: 'app-create-editorial',
  templateUrl: './create-editorial.component.html',
  styleUrls: ['./create-editorial.component.css'],
})
export class CreateEditorialComponent {
  editorial: FormGroup;
  title: FormControl;
  body: FormControl;

  constructor(private es: EditorialServiceService, private a: AuthService) {}

  ngOnInit() {
    console.log(this.a.userEmailId);
    (this.title = new FormControl('', [Validators.required])),
      (this.body = new FormControl('', [Validators.required])),
      (this.editorial = new FormGroup({
        newsTitle: this.title,
        newsBody: this.body,
      }));
  }

  onPublish() {
    var title = this.editorial.get('newsTitle').value;
    var body = this.editorial.get('newsBody').value;

    var editorialObj = {
      title: title,
      body: body,
    };

    this.editorial.reset();
    this.es.saveEditorial(editorialObj);
  }
}
