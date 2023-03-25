import { ComponentFixture, TestBed } from '@angular/core/testing';
import { EditorialContainerComponent } from '../editorial-container/editorial-container.component';

import { ViewEditorialComponent } from './view-editorial.component';

describe('ViewEditorialComponent', () => {
  let component: ViewEditorialComponent;
  let fixture: ComponentFixture<ViewEditorialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewEditorialComponent, EditorialContainerComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ViewEditorialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
