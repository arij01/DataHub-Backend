import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActionLog } from '../models/ActionLog';

@Injectable({
  providedIn: 'root'
})
export class ActionLogService {
  private baseUrl = 'http://localhost:8082/demo/action-logs';

  constructor(private http: HttpClient) { }

  getAllActionLogs(): Observable<ActionLog[]> {
    return this.http.get<ActionLog[]>(`${this.baseUrl}/all`);
  }

  createActionLog(actionLog: ActionLog): Observable<ActionLog> {
    return this.http.post<ActionLog>(`${this.baseUrl}/create`, actionLog);
  }
}
