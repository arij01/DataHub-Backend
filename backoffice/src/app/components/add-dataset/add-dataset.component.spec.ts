import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDatasetComponent } from './add-dataset.component';

describe('AddDatasetComponent', () => {
  let component: AddDatasetComponent;
  let fixture: ComponentFixture<AddDatasetComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddDatasetComponent]
    });
    fixture = TestBed.createComponent(AddDatasetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
