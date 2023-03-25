import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuard } from './admin.guard';
import { AuthGuard } from './auth.guard';
import { BackgroundComponent } from './background/background.component';
import { CreateEditorialComponent } from './editorials/create-editorial/create-editorial.component';
import { ExceptionComponent } from './exception/exception.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { BusinessNewsComponent } from './news/component/business-news/business-news.component';
import { ContainerComponent } from './news/component/container/container.component';
import { FavoriteComponent } from './news/component/favorite/favorite.component';
import { SearchComponent } from './news/component/search/search.component';
import { SportsNewsComponent } from './news/component/sports-news/sports-news.component';
import { ThumbnailComponent } from './news/component/thumbnail/thumbnail.component';
import { TopnewsComponent } from './news/component/topnews/topnews.component';
import { RegisterComponent } from './register/register.component';
import { ResetComponent } from './reset/reset.component';
import { ViewEditorialComponent } from './editorials/view-editorial/view-editorial.component';
import { EditorialReportComponent } from './editorials/editorial-report/editorial-report.component';
import { FavoriteEditorialsComponent } from './editorials/favorite-editorials/favorite-editorials.component';
import { UserGuard } from './user.guard';

const routes: Routes = [
  {
    path: 'register',
    component: RegisterComponent,
    // canActivate: [AuthGuard],
  },
  {
    path: 'login',
    component: LoginComponent,
    // canActivate: [AuthGuard]
  },
  {
    path: 'exception',
    component: ExceptionComponent,
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard, AdminGuard],
  },
  {
    path: 'editorial',
    children: [
      {
        path: 'create',
        component: CreateEditorialComponent,
        canActivate: [AdminGuard, AuthGuard],
      },
      {
        path: 'view',
        component: ViewEditorialComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'report',
        component: EditorialReportComponent,
        canActivate: [AuthGuard, AdminGuard],
      },
    ],
  },
  {
    path: 'news',
    children: [
      {
        path: 'top',
        canActivate: [AuthGuard, UserGuard],
        component: TopnewsComponent,
      },
      {
        path: 'sports',
        canActivate: [AuthGuard, UserGuard],
        component: SportsNewsComponent,
      },
      {
        path: 'thumbnail',
        canActivate: [AuthGuard],
        component: ThumbnailComponent,
      },

      {
        path: 'container',
        canActivate: [AuthGuard],
        component: ContainerComponent,
      },

      {
        path: 'business',
        canActivate: [AuthGuard, UserGuard],
        component: BusinessNewsComponent,
      },

      {
        path: 'search',
        canActivate: [AuthGuard, UserGuard],
        component: SearchComponent,
      },
      {
        path: 'fav',
        canActivate: [AuthGuard, UserGuard],
        component: FavoriteComponent,
      },
      {
        path: 'favEditorial',
        canActivate: [AuthGuard, UserGuard],
        component: FavoriteEditorialsComponent,
      },
    ],
  },

  { path: 'background', component: BackgroundComponent },
  { path: 'reset', component: ResetComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: 'background', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
