package tn.esprit.pi.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "dataobjects")
public class Dataobject {
    @Id
    @GeneratedValue
    private String idTechnologie;
    private String user;
    private String description;
    private String dataType;
    private String dataFormat;
}
