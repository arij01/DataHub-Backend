package tn.esprit.pi.model;

import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Column {

    private String id;
    private String nom;
    private String synonyme;
    private String type;
    private Label label;
    // Reflexive relationship
    private String businessKey;
    private String champResultant;

//    @OneToOne
//    private Documentation documentation;


}