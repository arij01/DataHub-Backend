import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageAfficherComponent } from './page-afficher.component';

describe('PageAfficherComponent', () => {
  let component: PageAfficherComponent;
  let fixture: ComponentFixture<PageAfficherComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageAfficherComponent]
    });
    fixture = TestBed.createComponent(PageAfficherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
