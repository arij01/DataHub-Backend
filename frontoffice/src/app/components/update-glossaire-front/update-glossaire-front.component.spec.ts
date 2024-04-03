import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateGlossaireFrontComponent } from './update-glossaire-front.component';

describe('UpdateGlossaireFrontComponent', () => {
  let component: UpdateGlossaireFrontComponent;
  let fixture: ComponentFixture<UpdateGlossaireFrontComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateGlossaireFrontComponent]
    });
    fixture = TestBed.createComponent(UpdateGlossaireFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
