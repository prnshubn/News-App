import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorialContainerComponent } from './editorial-container.component';

describe('EditorialContainerComponent', () => {
  let component: EditorialContainerComponent;
  let fixture: ComponentFixture<EditorialContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditorialContainerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditorialContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
