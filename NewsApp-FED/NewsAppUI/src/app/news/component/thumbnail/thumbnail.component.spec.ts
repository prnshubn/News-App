import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';

import { ThumbnailComponent } from './thumbnail.component';

describe('ThumbnailComponent', () => {
  let component: ThumbnailComponent;
  let fixture: ComponentFixture<ThumbnailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ThumbnailComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ThumbnailComponent);
    component = fixture.componentInstance;
    component.news = {
      title: 'Test News',
      sourceWebsiteName: 'Test Website',
      url: 'http://localhost:9876/',
      content: 'Test News Content',
      description: 'This is a test news article.',
      image: 'http://localhost:9876/test-news-image.jpg',
    };

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display news thumbnail', () => {
    const img = fixture.nativeElement.querySelector('img');
    expect(img).toBeTruthy();
    expect(img.src).toEqual(component.news.url);
  });

  it('should emit addNews event', () => {
    spyOn(component.addNews, 'emit');
    component.addToFavouriteList();
    expect(component.addNews.emit).toHaveBeenCalledWith(component.news);
  });

  it('should emit deleteNews event', () => {
    spyOn(component.deleteNews, 'emit');
    component.deleteNewsFav();
    expect(component.deleteNews.emit).toHaveBeenCalledWith(component.news);
  });

  it('should display the news title', () => {
    const titleElement = fixture.debugElement.query(By.css('.card-title'));
    expect(titleElement.nativeElement.textContent).toContain(
      component.news.title
    );
  });

  it('should display the news source', () => {
    const sourceElement = fixture.debugElement.query(By.css('.card-subtitle'));
    expect(sourceElement.nativeElement.textContent).toContain(
      component.news.sourceWebsiteName
    );
  });

  it('should display the news content', () => {
    const contentElement = fixture.debugElement.query(By.css('.card-text'));
    expect(contentElement.nativeElement.textContent).toContain(
      component.news.content
    );
  });

  it('should display "Add to favourites" button if favCheck is false', () => {
    component.favCheck = false;
    fixture.detectChanges();
    const addButton = fixture.debugElement.query(By.css('button:first-child'));
    expect(addButton.nativeElement.textContent.trim()).toBe(
      'Add to favourites'
    );
  });

  it('should display "Remove from favorites" button if favCheck is true', () => {
    component.favCheck = true;
    fixture.detectChanges();
    const removeButton = fixture.debugElement.query(
      By.css('button:last-child')
    );
    expect(removeButton.nativeElement.textContent.trim()).toBe('Read more');
  });

  it('should call addToFavouriteList() function when "Add to favourites" button is clicked', () => {
    spyOn(component, 'addToFavouriteList');
    component.favCheck = false;
    fixture.detectChanges();
    const addButton = fixture.debugElement.query(By.css('button:first-child'));
    addButton.triggerEventHandler('click', null);
    expect(component.addToFavouriteList).toHaveBeenCalled();
  });

  it('should display a link to the news URL', () => {
    const readMoreButton = fixture.debugElement.query(By.css('a'));
    expect(readMoreButton.nativeElement.href).toBe(component.news.url);
  });
});
