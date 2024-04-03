import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone:true,
  imports:[CommonModule],
  
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  


  Toggle(){

   const element= document.body as HTMLBodyElement 
  element.classList.toggle("toggle-sidebar")  
}
}
