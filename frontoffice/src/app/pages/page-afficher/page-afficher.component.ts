import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-page-afficher',
  templateUrl: './page-afficher.component.html',
  styleUrls: ['./page-afficher.component.css']
})
export class PageAfficherComponent implements OnInit {

  // Déclarez les méthodes 'add' et 'modify'
  add() {
    // Logique pour ajouter un champ
  }

  modify() {
    // Logique pour modifier la table
  }

  // Assurez-vous que traceabilityEntries est défini (peut-être dans le service ou dans ngOnInit)
  traceabilityEntries: any[] = []; // Assurez-vous que c'est du bon type ou initialisez avec des données par défaut

  ngOnInit() {
    // Initialiser traceabilityEntries ici si nécessaire
    // Exemple : this.traceabilityEntries = this.traceabilityService.getEntries();
  }
}