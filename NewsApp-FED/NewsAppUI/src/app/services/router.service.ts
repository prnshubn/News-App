import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BackgroundComponent } from '../background/background.component';

@Injectable({
  providedIn: 'root',
})
export class RouterService {
  routeToRecommended() {
    try {
      this.router.navigate(['']);
    } catch (error) {
      console.error(error);
    }
  }
  constructor(private router: Router) {}

  routeToLogin() {
    this.router.navigate(['background']);
  }

  routeToSearch() {
    this.router.navigate(['news/top']);
  }

  routeToAdmin() {
    this.router.navigate(['home']);
  }

  routeToException() {
    this.router.navigate(['exception']);
  }

  routeToEditorials() {
    this.router.navigate(['editorial/create']);
  }

  routeToReports() {
    this.router.navigate(['editorial/report']);
  }

  routeToEditorialsView() {
    this.router.navigate(['editorial/view']);
  }

  routeToFavEditorial() {
    this.router.navigate(['news/favEditorial']);
  }

  routeToFavNews() {
    this.router.navigate(['news/fav']);
  }
}
