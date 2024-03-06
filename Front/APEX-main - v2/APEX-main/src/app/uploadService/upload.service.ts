import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  url= 'http://localhost:8083/Automation/upload'; 

  constructor(private http: HttpClient) {}
  uploadFile(file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
  
    return this.http.post(this.url, formData);
  }
 
}
