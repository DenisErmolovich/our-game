import { TestBed } from '@angular/core/testing';

import { BalloonErrorService } from './balloon-error.service';

describe('BalloonErrorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BalloonErrorService = TestBed.get(BalloonErrorService);
    expect(service).toBeTruthy();
  });
});
