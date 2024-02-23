package tn.esprit.pi.entities;


import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "documentations")
public class Documentation {
    @Id
    private String id;
    private String title;
    private Date createdAt;
    private Date lastUpdated;
    private String text;

}