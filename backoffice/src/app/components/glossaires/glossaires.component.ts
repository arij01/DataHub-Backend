import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Datasets } from 'src/app/models/Datasets';
import { Glossaires } from 'src/app/models/Glossaires';
import { GlossairesService } from 'src/app/services/glossaires.service';

@Component({
  selector: 'app-glossaires',
  templateUrl: './glossaires.component.html',
  styleUrls: ['./glossaires.component.css']
})
export class GlossairesComponent {
  glossaires: Glossaires[] = [];

  constructor(private glossairesService: GlossairesService, private router: Router,) { }

  ngOnInit(): void {
    this.fetchGlossaires();
  }

  fetchGlossaires() {
    this.glossairesService.getAllGlossaires().subscribe({
      next: (data) => {
        this.glossaires = data;
      },
      error: (error) => console.log(error),
      complete: () => console.log('done')
    });
  }

  deleteGlossaire(id: number): void {
    this.glossairesService.deleteGlossaires(id).subscribe({
      next: () => {
        console.log('Glossaire deleted successfully!');
        this.fetchGlossaires();
      },
      error: (err: any) => {
        console.error('Error deleting Glossaire:', err);
      }
    });
  }

  updateGlossaire(glossaireID: number): void {
    // Implement your logic to update glossaire here
    this.router.navigate(['/updateGlossaire',glossaireID]);
  }
}
