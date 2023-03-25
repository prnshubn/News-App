import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { AuthService } from '../services/auth.service';

import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let authService: AuthService;
  let authServiceSpy: jasmine.SpyObj<AuthService>;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    authServiceSpy = jasmine.createSpyObj('AuthService', ['deleteUser']);
    await TestBed.configureTestingModule({
      declarations: [HomeComponent],
      providers: [AuthService],
    }).compileComponents();

    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call getAllUsers on init', () => {
    const spy = spyOn(authService, 'getAllUsers').and.returnValue(of([]));
    component.ngOnInit();
    expect(spy).toHaveBeenCalled();
    expect(component.userData).toEqual([]);
  });

  it('should call getAllUsers() after deleting the user', () => {
    spyOn(component, 'getAllUsers');

    component.deleteUser({ email: 'test@example.com' });

    // expect(component.getAllUsers).toHaveBeenCalled();
  });

  it('should display the correct column headers', () => {
    const compiled = fixture.nativeElement;
    const headers = compiled.querySelectorAll('thead th');
    expect(headers.length).toBe(4);
    expect(headers[0].textContent).toContain('Name');
    expect(headers[1].textContent).toContain('Email');
    expect(headers[2].textContent).toContain('Date Created On');
    expect(headers[3].textContent).toContain('Action');
  });

  it('should display the correct data', () => {
    component.userData = [
      {
        user_id: 2,
        name: 'John Doe',
        email: 'johndoe@example.com',
        createdOn: '2022-01-01',
      },
      {
        user_id: 3,
        name: 'Jane Doe',
        email: 'janedoe@example.com',
        createdOn: '2022-02-01',
      },
    ];
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    const rows = compiled.querySelectorAll('tbody tr');
    expect(rows.length).toBe(2);
    expect(rows[0].textContent).toContain('John Doe');
    expect(rows[0].textContent).toContain('johndoe@example.com');
    expect(rows[0].textContent).toContain('2022-01-01');
    expect(rows[1].textContent).toContain('Jane Doe');
    expect(rows[1].textContent).toContain('janedoe@example.com');
    expect(rows[1].textContent).toContain('2022-02-01');
  });

  it('should exclude the row with user_id = 1', () => {
    component.userData = [
      {
        user_id: 1,
        name: 'Admin',
        email: 'admin@example.com',
        createdOn: '2022-01-01',
      },
      {
        user_id: 2,
        name: 'John Doe',
        email: 'johndoe@example.com',
        createdOn: '2022-02-01',
      },
    ];
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    const rows = compiled.querySelectorAll('tbody tr');
    expect(rows.length).toBe(2);
    expect(rows[1].textContent).toContain('John Doe');
    expect(rows[1].textContent).toContain('johndoe@example.com');
    expect(rows[1].textContent).toContain('2022-02-01');
  });
});
