package tn.esprit.pi.entities;
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
}