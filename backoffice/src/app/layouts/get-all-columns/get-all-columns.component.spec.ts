import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAllColumnsComponent } from './get-all-columns.component';

describe('GetAllColumnsComponent', () => {
  let component: GetAllColumnsComponent;
  let fixture: ComponentFixture<GetAllColumnsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetAllColumnsComponent]
    });
    fixture = TestBed.createComponent(GetAllColumnsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
