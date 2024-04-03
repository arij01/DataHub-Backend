import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGlossaireFrontComponent } from './add-glossaire-front.component';

describe('AddGlossaireFrontComponent', () => {
  let component: AddGlossaireFrontComponent;
  let fixture: ComponentFixture<AddGlossaireFrontComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddGlossaireFrontComponent]
    });
    fixture = TestBed.createComponent(AddGlossaireFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
