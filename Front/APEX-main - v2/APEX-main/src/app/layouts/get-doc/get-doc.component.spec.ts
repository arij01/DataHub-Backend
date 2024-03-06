import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetDocComponent } from './get-doc.component';

describe('GetDocComponent', () => {
  let component: GetDocComponent;
  let fixture: ComponentFixture<GetDocComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetDocComponent]
    });
    fixture = TestBed.createComponent(GetDocComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
