package tn.esprit.pi.repositories;


import tn.esprit.pi.entities.Column;
import tn.esprit.pi.entities.Documentation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Label;

import java.util.List;

@Repository
public interface DocumentationRepository extends MongoRepository<Documentation, String> {
    Documentation findByTitle(String title);
}