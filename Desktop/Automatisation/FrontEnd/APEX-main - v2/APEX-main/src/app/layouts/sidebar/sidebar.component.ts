import { Component } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  Toggle(){

    const element= document.body as HTMLBodyElement 
   element.classList.toggle("toggle-sidebar")  
 }
 
}
