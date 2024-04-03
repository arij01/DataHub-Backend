import './Feedback';
import { FeedbackGiven } from './Feedback';
import  './Glossaires';
import { Glossaires } from './Glossaires';

export class Datasets {
  id!: number;
  nom!: string;
  description!: string;
  proprietaire!: string;
  dateAjout!: Date;
  file!: File; // Change the type to File
  image!: string;
  tags!: string;
  glossaires!: Glossaires[];
  fileName!: string;
  filePath!: string;
  imagePath!: string;
  imageName!: string;
}
  
