import { FileLogService } from '../file-log.service';
import { FileLog } from '../file-log';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-file-log-create',
  templateUrl: './file-log-create.component.html',
  styleUrls: ['./file-log-create.component.css']
})
export class FileLogCreateComponent implements OnInit {

  fileLog: FileLog = new FileLog();
  submitted = false;

  constructor(private fileLogService: FileLogService,
    private router: Router) { }

  ngOnInit() {
  }

  newFileLog(): void {
    this.submitted = false;
    this.fileLog = new FileLog();
  }

  save() {
    this.fileLogService.createFileLog(this.fileLog)
      .subscribe(data => console.log(data), error => console.log(error));
    this.fileLog = new FileLog();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/fileLogs']);
  }
}