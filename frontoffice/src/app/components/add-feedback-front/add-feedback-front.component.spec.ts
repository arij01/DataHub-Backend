import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFeedbackFrontComponent } from './add-feedback-front.component';

describe('AddFeedbackFrontComponent', () => {
  let component: AddFeedbackFrontComponent;
  let fixture: ComponentFixture<AddFeedbackFrontComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddFeedbackFrontComponent]
    });
    fixture = TestBed.createComponent(AddFeedbackFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
