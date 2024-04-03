import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TraceabilityService {
  private apiUrl = 'http://localhost:8081/demo/api/traceability'; // Remplacez ceci par l'URL réelle de votre API

  constructor(private http: HttpClient) { }

  // Méthode pour récupérer toutes les entrées de traçabilité
  getTraceabilityEntries(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/entries`);
  }

  // Méthode pour récupérer une entrée de traçabilité par ID
  getTraceabilityEntryById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/entries/${id}`);
  }

  // Méthode pour ajouter une nouvelle entrée de traçabilité
  addTraceabilityEntry(entry: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/entries`, entry);
  }

  // Méthode pour mettre à jour une entrée de traçabilité
  updateTraceabilityEntry(id: number, updatedEntry: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/entries/${id}`, updatedEntry);
  }

  // Méthode pour supprimer une entrée de traçabilité
  deleteTraceabilityEntry(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/entries/${id}`);
  }
}
