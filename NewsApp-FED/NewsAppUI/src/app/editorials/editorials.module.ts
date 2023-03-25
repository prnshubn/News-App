import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateEditorialComponent } from './create-editorial/create-editorial.component';
import { ViewEditorialComponent } from './view-editorial/view-editorial.component';
import { EditorialThumbnailComponent } from './editorial-thumbnail/editorial-thumbnail.component';
import { EditorialContainerComponent } from './editorial-container/editorial-container.component';
import { EditorialServiceService } from './editorial-service.service';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { EditorialReportComponent } from './editorial-report/editorial-report.component';
import { FavoriteEditorialsComponent } from './favorite-editorials/favorite-editorials.component';

@NgModule({
  declarations: [
    CreateEditorialComponent,
    ViewEditorialComponent,
    EditorialThumbnailComponent,
    EditorialContainerComponent,
    EditorialReportComponent,
    FavoriteEditorialsComponent,
  ],
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  providers: [
    ViewEditorialComponent,
    EditorialServiceService,
    EditorialContainerComponent,
    CreateEditorialComponent,
    FavoriteEditorialsComponent,
  ],
})
export class EditorialsModule {}
