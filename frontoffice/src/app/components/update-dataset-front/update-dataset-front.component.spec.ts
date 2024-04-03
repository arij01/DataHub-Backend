import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDatasetFrontComponent } from './update-dataset-front.component';

describe('UpdateDatasetFrontComponent', () => {
  let component: UpdateDatasetFrontComponent;
  let fixture: ComponentFixture<UpdateDatasetFrontComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateDatasetFrontComponent]
    });
    fixture = TestBed.createComponent(UpdateDatasetFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
