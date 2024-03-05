package tn.esprit.pi.entities;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "columns")
public class Column {
    @Id
    private String id;
    private String column_name;
    private String synonym;
    private String data_type;
    private Label label;

    // Reflexive relationship
    private String parentId;
    @ManyToOne
    @JoinColumn(name = "documentation_id")
    private Documentation doc;
}