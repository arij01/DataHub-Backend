import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateFeedbackFrontComponent } from './update-feedback-front.component';

describe('UpdateFeedbackFrontComponent', () => {
  let component: UpdateFeedbackFrontComponent;
  let fixture: ComponentFixture<UpdateFeedbackFrontComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateFeedbackFrontComponent]
    });
    fixture = TestBed.createComponent(UpdateFeedbackFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
