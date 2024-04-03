import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GlossairesComponent } from './glossaires.component';

describe('GlossairesComponent', () => {
  let component: GlossairesComponent;
  let fixture: ComponentFixture<GlossairesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GlossairesComponent]
    });
    fixture = TestBed.createComponent(GlossairesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
