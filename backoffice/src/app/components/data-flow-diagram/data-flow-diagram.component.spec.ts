import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataFlowDiagramComponent } from './data-flow-diagram.component';

describe('DataFlowDiagramComponent', () => {
  let component: DataFlowDiagramComponent;
  let fixture: ComponentFixture<DataFlowDiagramComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DataFlowDiagramComponent]
    });
    fixture = TestBed.createComponent(DataFlowDiagramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
