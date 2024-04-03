import { Column } from "./columns";

export class Documentation {
  id: string;
  title: string;
  createdAt: Date;
  lastUpdated: Date;
  text: string;
  
    constructor(id: string,title: string,createdAt: Date,lastUpdated: Date,text: string,) {
      this.id = id;
      this.title=title;
      this.createdAt=createdAt;
      this.lastUpdated=lastUpdated;
      this.text = text;
      
    }
  }