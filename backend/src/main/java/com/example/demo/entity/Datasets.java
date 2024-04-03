package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Datasets implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String tags;
    private String proprietaire;
    private LocalDate dateAjout;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;


    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "image_name")
    private String imageName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="dataset")
    @JsonIgnore
    private Set<Feedbacks> Feedbackssdata= new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy="dataset")
    @JsonIgnore
    private Set<Glossaires> Glossairesdata = new HashSet<>();

}

