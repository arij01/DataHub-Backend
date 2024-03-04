package tn.esprit.pi.entities;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "columns")
public class Column {
    @Id
    private String id;
    private String nom;
    private String synonyme;
    private String type;
    @Enumerated
    private Label label;
    // Reflexive relationship
    private String businessKey;
    private String champResultant;

    @OneToOne
    private Documentation documentation;

}