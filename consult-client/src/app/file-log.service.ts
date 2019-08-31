import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileLogService {

  private baseUrl = 'http://localhost:8090/filelog';

  constructor(private http: HttpClient) { }

  getFileLog(id: BigInteger): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createFileLog(filelog: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, filelog);
  }

  getFileLogList(): Observable<any> {
    let params = new HttpParams();
    params = params.append('page', "0");
    params = params.append('size', "50");
    return this.http.get(`${this.baseUrl}`,{params: params});
  }
}