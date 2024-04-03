import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Glossaires } from 'src/app/models/Glossaires';
import { GlossairesService } from 'src/app/services/glossaires.service';

@Component({
  selector: 'app-update-glossaires',
  templateUrl: './update-glossaires.component.html',
  styleUrls: ['./update-glossaires.component.css']
})
export class UpdateGlossairesComponent {

  aFormGroup!: FormGroup;
  glossaire: Glossaires = new Glossaires();
  id!: number;

  constructor(
    private glossairesService: GlossairesService,
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
  ) {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id']; 
    });
  }
  
  ngOnInit() {
    this.aFormGroup = this.formBuilder.group({
      recaptcha: ['', Validators.required]
    });
    // Fetch the glossaire details
    this.fetchGlossaireDetails();
  }

  fetchGlossaireDetails() {
    this.glossairesService.getGlossairesById(this.id).subscribe({
      next: (data) => {
        this.glossaire = data;
      },
      error: (error) => console.log(error),
      complete: () => console.log('done')
    });
  }

  update(form: NgForm) {
    if (form.valid) {
      
        if (this.id != undefined) {
        
          this.glossairesService.updateGlossaires(this.id, this.glossaire).subscribe({
            next: () => {
              console.log("Glossaire updated successfully!");
              this.router.navigate(['/glossaires']); 
            },
            error: (err) => console.log(err)
          });
        } else {
          console.error('Dataset not found');
        }
     
    }
  }

  siteKey: string = "6LeBnZUpAAAAAEDMtn5PQAEpTInPp0rB_fR60D-A";

}
