import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ResetComponent } from './reset.component';

describe('ResetComponent', () => {
  let component: ResetComponent;
  let fixture: ComponentFixture<ResetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule],
      declarations: [ResetComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ResetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should validate name field', () => {
    const name = component.resetForm.controls['name'];
    expect(name.valid).toBeFalsy();
  });

  it('should validate password field', () => {
    const password = component.resetForm.controls['password'];
    expect(password.valid).toBeFalsy();
  });

  it('should call onReset method when form is submitted', () => {
    spyOn(component, 'onReset');
    const form = fixture.nativeElement.querySelector('form');
    form.dispatchEvent(new Event('submit'));
    expect(component.onReset).toHaveBeenCalled();
  });
});
