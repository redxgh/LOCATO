import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { AuthGuard } from './auth.guard';

describe('AuthGuard', () => {
  let guard: AuthGuard; // Declare the guard variable

  const executeGuard: CanActivateFn = (...guardParameters) =>
      TestBed.runInInjectionContext(() => guard.canActivate(...guardParameters)); // Use guard instance

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AuthGuard); // Instantiate the AuthGuard
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
