import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditorialReportComponent } from './editorial-report.component';

describe('EditorialReportComponent', () => {
  let component: EditorialReportComponent;
  let fixture: ComponentFixture<EditorialReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditorialReportComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditorialReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
