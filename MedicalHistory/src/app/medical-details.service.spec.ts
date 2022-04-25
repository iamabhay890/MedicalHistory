import { TestBed } from '@angular/core/testing';

import { MedicalDetailsService } from './medical-details.service';

describe('MedicalDetailsService', () => {
  let service: MedicalDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicalDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
