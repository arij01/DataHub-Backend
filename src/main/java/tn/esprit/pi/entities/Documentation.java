package tn.esprit.pi.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "documentations")
public class Documentation {
    @Id
    private String id;
    private String text;
}