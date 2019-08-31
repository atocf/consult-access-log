import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FileLogListComponent } from './file-log-list.component';

describe('FileLogListComponent', () => {
  let component: FileLogListComponent;
  let fixture: ComponentFixture<FileLogListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileLogListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileLogListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
