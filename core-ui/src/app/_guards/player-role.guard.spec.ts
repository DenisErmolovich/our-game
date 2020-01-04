import { TestBed, async, inject } from '@angular/core/testing';

import { PlayerRoleGuard } from './player-role.guard';

describe('PlayerRoleGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PlayerRoleGuard]
    });
  });

  it('should ...', inject([PlayerRoleGuard], (guard: PlayerRoleGuard) => {
    expect(guard).toBeTruthy();
  }));
});
