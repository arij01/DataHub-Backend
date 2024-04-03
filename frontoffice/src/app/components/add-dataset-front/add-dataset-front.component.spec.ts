import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDatasetFrontComponent } from './add-dataset-front.component';

describe('AddDatasetFrontComponent', () => {
  let component: AddDatasetFrontComponent;
  let fixture: ComponentFixture<AddDatasetFrontComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddDatasetFrontComponent]
    });
    fixture = TestBed.createComponent(AddDatasetFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
