package tn.esprit.pi.entities;

import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "solutions")
public class Solution {
    @Id
    private String id;
    private String fournisseur;
    @Enumerated
    private Typecout typecout;
    @Enumerated
    private Typesolution typesolution;
    @OneToOne
    private File file;
    @OneToOne
    private Dataobject dataobject;
}
