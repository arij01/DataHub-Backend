import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Feedbacks } from 'src/app/models/Feedback';
import { FeedbacksService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-update-feedback',
  templateUrl: './update-feedback.component.html',
  styleUrls: ['./update-feedback.component.css']
})
export class UpdateFeedbackComponent {

  aFormGroup!: FormGroup;
  feedback: Feedbacks = new Feedbacks();
  id: number;

  constructor(
    private feedbacksService: FeedbacksService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,

  ) {
    this.id = +this.route.snapshot.params['id'];
  }
  ngOnInit() {
    this.aFormGroup = this.formBuilder.group({
      recaptcha: ['', Validators.required]
    });
  }
  update(form: NgForm) {
    if (form.valid) {
      this.feedbacksService.updateFeedbacks(this.id,this.feedback).subscribe({
        next: () => {
          console.log("dataset updated successfully!");
          this.router.navigate(['/getAll']);
        },
        error: (err) => console.log(err)
      });
    }
  }
  siteKey : string="6LeBnZUpAAAAAEDMtn5PQAEpTInPp0rB_fR60D-A";

}

