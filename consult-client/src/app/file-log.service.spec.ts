import { TestBed } from '@angular/core/testing';

import { FileLogService } from './file-log.service';

describe('FileLogService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FileLogService = TestBed.get(FileLogService);
    expect(service).toBeTruthy();
  });
});
