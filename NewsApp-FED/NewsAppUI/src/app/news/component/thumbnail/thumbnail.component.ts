import { Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css'],
})
export class ThumbnailComponent implements OnInit {
  @Input()
  news: any;
  @Output()
  addNews = new EventEmitter();
  @Output()
  deleteNews = new EventEmitter();
  @Input()
  favCheck: boolean = false;
  isSearch: boolean = false;
  newsURL: string = '';

  constructor() {}

  ngOnInit() {
    this.newsURL = this.news.url;
    if (this.news.newsType == 'search') {
      this.isSearch = true;
      console.log('Search= ' + this.isSearch);
      console.log('Favcheck = ' + this.favCheck);
    }
  }

  addToFavouriteList() {
    this.addNews.emit(this.news);
  }
  deleteNewsFav() {
    console.log(this.news);
    this.deleteNews.emit(this.news);
  }
}
