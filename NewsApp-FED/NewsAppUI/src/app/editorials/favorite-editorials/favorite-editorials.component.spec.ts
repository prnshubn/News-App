import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EditorialContainerComponent } from '../editorial-container/editorial-container.component';

import { FavoriteEditorialsComponent } from './favorite-editorials.component';

describe('FavoriteEditorialsComponent', () => {
  let component: FavoriteEditorialsComponent;
  let fixture: ComponentFixture<FavoriteEditorialsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FavoriteEditorialsComponent, EditorialContainerComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(FavoriteEditorialsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
