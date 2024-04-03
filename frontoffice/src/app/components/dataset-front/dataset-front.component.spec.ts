import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatasetFrontComponent } from './dataset-front.component';

describe('DatasetFrontComponent', () => {
  let component: DatasetFrontComponent;
  let fixture: ComponentFixture<DatasetFrontComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DatasetFrontComponent]
    });
    fixture = TestBed.createComponent(DatasetFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
