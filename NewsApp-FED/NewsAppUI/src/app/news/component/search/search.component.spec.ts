import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { of } from 'rxjs';
import { NewsService } from '../../news.service';
import { ContainerComponent } from '../container/container.component';

import { SearchComponent } from './search.component';

describe('SearchComponent', () => {
  let component: SearchComponent;
  let newsService: NewsService;
  let newsServiceSpy: jasmine.SpyObj<NewsService>;
  let fixture: ComponentFixture<SearchComponent>;

  beforeEach(async () => {
    newsServiceSpy = jasmine.createSpyObj('NewsService', ['searchNews']);
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule],
      declarations: [SearchComponent, ContainerComponent],
      providers: [{ provide: NewsService, useValue: newsServiceSpy }],
    }).compileComponents();

    fixture = TestBed.createComponent(SearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    newsService = TestBed.inject(NewsService);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a search form with a keyword input', () => {
    const keywordInput = fixture.nativeElement.querySelector('#search');
    expect(keywordInput).toBeTruthy();
  });

  it('should initialize the keyword form control with an empty string', () => {
    expect(component.keyword.value).toBe('');
  });

  it('should set the value of the keyword input to an empty string on initialization', () => {
    const keywordInput: HTMLInputElement =
      fixture.nativeElement.querySelector('#search');
    expect(keywordInput.value).toBe('');
  });

  it('should call searchSubmit() method', () => {
    // Arrange
    const newsServiceSpy = jasmine.createSpyObj('NewsService', ['searchNews']);
    const routerSeviceSpy = jasmine.createSpyObj('RouterService', [
      'routeToException',
    ]);
    const editorialServiceSpy = jasmine.createSpyObj(
      'EditorialServiceService',
      ['openSnackBar']
    );
    const component = new SearchComponent(
      newsServiceSpy,
      routerSeviceSpy,
      editorialServiceSpy
    );
    spyOn(component, 'searchSubmit');

    // Act
    component.searchSubmit();

    // Assert
    expect(component.searchSubmit).toHaveBeenCalled();
  });
});
