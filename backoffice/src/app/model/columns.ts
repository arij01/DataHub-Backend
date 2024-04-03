import { Documentation } from "./documentation";
import { Label } from "./label";

export class Column {
    id: string;
    nom: string;
    synonyme: string;
    type: string;
    label: Label;

    // Reflexive relationship
    businessKey: string;
    //Relation
    doc: Documentation;
    
    constructor(id: string, nom: string, synonyme: string, type: string,businessKey: string, label: Label, doc: Documentation) {
        this.id = id;
        this.nom = nom;
        this.synonyme = synonyme;
        this.type = type;
        this.label = label;
        this.businessKey = businessKey;
        this.doc = doc;
    }
   


}