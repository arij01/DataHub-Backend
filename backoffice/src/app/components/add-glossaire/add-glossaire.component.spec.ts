import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGlossaireComponent } from './add-glossaire.component';

describe('AddGlossaireComponent', () => {
  let component: AddGlossaireComponent;
  let fixture: ComponentFixture<AddGlossaireComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddGlossaireComponent]
    });
    fixture = TestBed.createComponent(AddGlossaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
