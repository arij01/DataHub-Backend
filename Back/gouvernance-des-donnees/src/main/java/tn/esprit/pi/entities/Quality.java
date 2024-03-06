package tn.esprit.pi.entities;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Quality")
public class Quality {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double completeness; // Exemple de métrique de complétude
    private Boolean accuracy;    // Exemple de métrique d'exactitude

    // Nouveaux champs pour la correction manuelle
    private String incompleteValue; // Exemple de valeur manquante ou incorrece

    public void setCompleteness(Double completeness) {
        this.completeness = completeness;
    }

    public void setAccuracy(Boolean accuracy) {
        this.accuracy = accuracy;
    }

    public void setIncompleteValue(String incompleteValue) {
        this.incompleteValue = incompleteValue;
    }
}