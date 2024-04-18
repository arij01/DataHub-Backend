import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NotificationService } from 'src/app/NotificationService/notification.service';

@Component({
  selector: 'app-header',
  standalone:true,
  imports:[CommonModule],
  
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(public notificationService: NotificationService) {}


  Toggle(){

   const element= document.body as HTMLBodyElement 
  element.classList.toggle("toggle-sidebar")  
}
}
