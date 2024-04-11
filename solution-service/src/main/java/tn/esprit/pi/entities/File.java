package tn.esprit.pi.entities;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "files")
public class File {
    @Id
    private String id;
    private byte[] bytes;
    private String name;
    private long size;
    private String type;

    @OneToOne
    private Solution solution;
}
