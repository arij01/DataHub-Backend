import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Documentation } from '../model/documentation';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DocService {
  url: string = 'http://localhost:8081/api/documentations'

  constructor(private http:HttpClient) { }
  // public saveDocumentation(documentation: Documentation): Observable<Documentation> {
  //   return this.http.post<Documentation>(this.url, documentation);
  // }

  public getDocumentationById(id: string){
    return this.http.get<Documentation>(this.url +'/get/'+ id);
  }

  // public updateDocumentation(documentation: Documentation) {
  //   return this.http.put<Documentation>(this.url+'update', documentation);
  // }
  public updateDocById(id: string,documentation: Documentation) {
    return this.http.put<Documentation>(this.url+'/update/'+ id, documentation);
  }

  // deleteDocumentation(id: string){
  //   return this.http.delete(this.url+'delete/'+id);
  // }
  

}
