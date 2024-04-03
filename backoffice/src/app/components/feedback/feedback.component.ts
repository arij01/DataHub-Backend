import { Component } from '@angular/core';
import { Feedbacks } from 'src/app/models/Feedback';
import { FeedbacksService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent {
  feedbacks: Feedbacks[] = [];
  constructor(private feedbacksService: FeedbacksService) { }
  ngOnInit(): void {
    this.fetchDatasets();


  }

  fetchDatasets() {
    this.feedbacksService.getAllFeedbacks().subscribe({
      next: (data) => {
        this.feedbacks = data;
      },
      error: (error) => console.log(error),
      complete: () => console.log('done')
    });
  }

}
