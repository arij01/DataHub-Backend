import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateGlossairesComponent } from './update-glossaires.component';

describe('UpdateGlossairesComponent', () => {
  let component: UpdateGlossairesComponent;
  let fixture: ComponentFixture<UpdateGlossairesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateGlossairesComponent]
    });
    fixture = TestBed.createComponent(UpdateGlossairesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
