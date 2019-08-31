import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FileLogCreateComponent } from './file-log-create.component';

describe('FileLogCreateComponent', () => {
  let component: FileLogCreateComponent;
  let fixture: ComponentFixture<FileLogCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileLogCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileLogCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
