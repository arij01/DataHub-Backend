import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Datasets } from '../models/Datasets';

@Injectable({
  providedIn: 'root'
})
export class DatasetsService {
  private baseUrl = 'http://localhost:8082/demo/datasets';

  constructor(private http: HttpClient) { }

  getAllDatasets(): Observable<Datasets[]> {
    return this.http.get<Datasets[]>(`${this.baseUrl}/all`);
  }

  getDatasetById(id: number): Observable<Datasets> {
    return this.http.get<Datasets>(`${this.baseUrl}/${id}`);
  }

  addDataset(dataset: Datasets): Observable<Datasets> {
    return this.http.post<Datasets>(`${this.baseUrl}/add`, dataset);
  }

  updateDataset(id: number, updatedDataset: Datasets): Observable<Datasets> {
    return this.http.put<Datasets>(`${this.baseUrl}/update/${id}`, updatedDataset);
  }

  deleteDataset(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }

  // Method to upload file for dataset
  uploadFile(datasetId: number, file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post(`${this.baseUrl}/${datasetId}/file`, formData);
  }

  // Method to upload image for dataset
  uploadImage(datasetId: number, image: File): Observable<any> {
    const formData = new FormData();
    formData.append('image', image);

    return this.http.post(`${this.baseUrl}/${datasetId}/image`, formData);
  }
}
