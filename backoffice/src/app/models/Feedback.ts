import { Datasets } from "./Datasets";

export class Feedbacks {
  id!: number;
  utilisateur!: string;
  typeFeedback!: FeedbackGiven;
  description!: string;
  datasetAssocie !: string;
  date!: Date;
  statut!: StatusGiven;
  datasetid!: number;
  dataset!: Datasets;

}

export enum FeedbackGiven {
  Excellent = 'Excellent',
  VeryGood = 'Very Good',
  Good = 'Good',
  QuiteWell = 'Quite Well',
  Bad = 'Bad'
}

export enum StatusGiven {
  Open = 'Open',
  Closed = 'Closed'
}
