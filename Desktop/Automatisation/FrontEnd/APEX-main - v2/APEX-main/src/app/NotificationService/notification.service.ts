import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private messageSubject = new BehaviorSubject<string>(''); // Observable pour le message
  public message$ = this.messageSubject.asObservable(); // Observable public pour le message

  constructor() { }

  updateMessage(message: string): void {
    
    this.messageSubject.next(message);
  }
}
