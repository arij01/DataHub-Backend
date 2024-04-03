import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Glossaires } from '../models/Glossaires';
import { Datasets } from '../models/Datasets';

@Injectable({
  providedIn: 'root'
})
export class GlossairesService {
  private baseUrl = 'http://localhost:8082/demo/glossaires';

  constructor(private http: HttpClient) { }

  getAllGlossaires(): Observable<Glossaires[]> {
    return this.http.get<Glossaires[]>(`${this.baseUrl}/all`);
  }

  getGlossairesById(id: number): Observable<Glossaires> {
    return this.http.get<Glossaires>(`${this.baseUrl}/${id}`);
  }

  addGlossaires(glossaire: Glossaires, datasetId: number): Observable<any> {
    // Include datasetId in the glossaire object
    glossaire.dataset = { id: datasetId } as Datasets;
    return this.http.post<any>(`${this.baseUrl}/add`, glossaire);
  }
  
  
  updateGlossaires(id: number, updatedGlossaires: Glossaires): Observable<any> {
    return this.http.put<Glossaires>(`${this.baseUrl}/update/${id}`, updatedGlossaires);
  }

  deleteGlossaires(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
  
  getAllDatasets(): Observable<Datasets[]> {
    return this.http.get<Datasets[]>('http://localhost:8082/demo/datasets'); // Replace the URL with your actual endpoint for fetching datasets
  }
}
