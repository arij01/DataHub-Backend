import { TestBed } from '@angular/core/testing';

import { GlossairesService } from './glossaires.service';

describe('GlossairesService', () => {
  let service: GlossairesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GlossairesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
