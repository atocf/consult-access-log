import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { FileLogService } from "../file-log.service";
import { FileLog } from "../file-log";
import { Router } from '@angular/router';

@Component({
  selector: 'app-file-log-list',
  templateUrl: './file-log-list.component.html',
  styleUrls: ['./file-log-list.component.css']
})
export class FileLogListComponent implements OnInit {

  private _fileLogs: Observable<FileLog[]>;
  
  public get fileLogs(): Observable<FileLog[]> {
    return this._fileLogs;
  }
  public set fileLogs(value: Observable<FileLog[]>) {
    this._fileLogs = value;
  }
  
  constructor(private fileLogService: FileLogService, private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.fileLogs = this.fileLogService.getFileLogList();
  }
}
