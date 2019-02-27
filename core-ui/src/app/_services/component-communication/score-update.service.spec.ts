import { TestBed } from '@angular/core/testing';

import { ScoreUpdateService } from './score-update.service';

describe('ScoreUpdateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ScoreUpdateService = TestBed.get(ScoreUpdateService);
    expect(service).toBeTruthy();
  });
});
