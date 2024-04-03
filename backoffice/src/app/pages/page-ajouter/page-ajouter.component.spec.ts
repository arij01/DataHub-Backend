import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageAjouterComponent } from './page-ajouter.component';

describe('PageAjouterComponent', () => {
  let component: PageAjouterComponent;
  let fixture: ComponentFixture<PageAjouterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageAjouterComponent]
    });
    fixture = TestBed.createComponent(PageAjouterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
