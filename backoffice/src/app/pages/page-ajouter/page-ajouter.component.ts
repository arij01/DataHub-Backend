// page-ajouter.component.ts
import { Component } from '@angular/core';
import { TraceabilityService } from 'src/app/TraceabilityServices/traceability.service';

@Component({
  selector: 'app-page-ajouter',
  templateUrl: './page-ajouter.component.html',
  styleUrls: ['./page-ajouter.component.css']
})
export class PageAjouterComponent {
  traceabilityEntry: any = {}; // Assurez-vous que cet objet est initialisé correctement avec les propriétés nécessaires

  constructor(private traceabilityService: TraceabilityService) { }

  // Méthode pour gérer la soumission du formulaire
  onSubmit(): void {
    this.traceabilityService.addTraceabilityEntry(this.traceabilityEntry)
      .subscribe(
        response => {
          console.log('Entrée ajoutée avec succès :', response);
          // Réinitialisez le formulaire ou effectuez d'autres actions si nécessaire
          this.traceabilityEntry = {};
        },
        error => {
          console.error('Erreur lors de l\'ajout de l\'entrée :', error);
          // Gérez les erreurs ici
        }
      );
  }
}
