import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private http: HttpClient) { }

  public loadMessagesFromFile(filePath: string): Observable<string> {
    return this.http.get(filePath, { responseType: 'text' });
  }
}
