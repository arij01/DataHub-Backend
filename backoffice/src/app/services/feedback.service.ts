import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Feedbacks } from '../models/Feedback';

@Injectable({
  providedIn: 'root'
})
export class FeedbacksService {
  private baseUrl = 'http://localhost:8082/demo/feedbacks';

  constructor(private http: HttpClient) { }

  getAllFeedbacks(): Observable<Feedbacks[]> {
    return this.http.get<Feedbacks[]>(`${this.baseUrl}/all`);
  }

  getFeedbacksById(id: number): Observable<Feedbacks> {
    return this.http.get<Feedbacks>(`${this.baseUrl}/${id}`);
  }

  addFeedbacks(feedbacks: Feedbacks): Observable<Feedbacks> {
    return this.http.post<Feedbacks>(`${this.baseUrl}/add`, feedbacks);
  }

  updateFeedbacks(id: number, updatedFeedbacks: Feedbacks): Observable<Feedbacks> {
    return this.http.put<Feedbacks>(`${this.baseUrl}/update/${id}`, updatedFeedbacks);
  }

  deleteFeedbacks(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
}
