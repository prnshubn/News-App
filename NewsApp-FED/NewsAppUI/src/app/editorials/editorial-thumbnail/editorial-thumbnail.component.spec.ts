import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorialThumbnailComponent } from './editorial-thumbnail.component';

describe('EditorialThumbnailComponent', () => {
  let component: EditorialThumbnailComponent;
  let fixture: ComponentFixture<EditorialThumbnailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditorialThumbnailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditorialThumbnailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
