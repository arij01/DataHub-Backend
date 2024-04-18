import { Injectable } from '@angular/core';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WebSocketServiceService {
  private stompClient: any;
  private notificationsSubject: Subject<string> = new Subject<string>();

  constructor() { }

  public connect(): void {
    const socket = new SockJS('/ws');
    this.stompClient = new Stomp.Client();
    this.stompClient.connect({}, (frame: any) => {
      this.stompClient.subscribe('/topic/notifications', (notification: any) => {
        this.notificationsSubject.next(JSON.parse(notification.body));
      });
    });
  }

  public getNotifications(): Subject<string> {
    return this.notificationsSubject;
  }
}
