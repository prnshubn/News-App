import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopnewsComponent } from './component/topnews/topnews.component';
import { SportsNewsComponent } from './component/sports-news/sports-news.component';

import { ContainerComponent } from './component/container/container.component';

import { ThumbnailComponent } from './component/thumbnail/thumbnail.component';
import { NewsService } from './news.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from './interceptor.service';
import { BusinessNewsComponent } from './component/business-news/business-news.component';
import { SearchComponent } from './component/search/search.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { FavoriteComponent } from './component/favorite/favorite.component';

@NgModule({
  declarations: [
    TopnewsComponent,
    SportsNewsComponent,
    ContainerComponent,
    ThumbnailComponent,
    BusinessNewsComponent,
    SearchComponent,
    FavoriteComponent,
  ],
  imports: [CommonModule, ReactiveFormsModule, MatInputModule],
  providers: [
    ContainerComponent,
    TopnewsComponent,
    SportsNewsComponent,
    BusinessNewsComponent,
    SearchComponent,
    NewsService,
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true },
  ],
})
export class NewsModule {}
