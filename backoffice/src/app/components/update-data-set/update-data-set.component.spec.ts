import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDataSetComponent } from './update-data-set.component';

describe('UpdateDataSetComponent', () => {
  let component: UpdateDataSetComponent;
  let fixture: ComponentFixture<UpdateDataSetComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateDataSetComponent]
    });
    fixture = TestBed.createComponent(UpdateDataSetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
