import { TestBed } from '@angular/core/testing';

import { CollabouxComponentsService } from './collaboux-components.service';

describe('CollabouxComponentsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CollabouxComponentsService = TestBed.get(CollabouxComponentsService);
    expect(service).toBeTruthy();
  });
});
