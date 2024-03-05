package tn.esprit.pi.repositories;


import tn.esprit.pi.entities.Documentation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentationRepository extends MongoRepository<Documentation, String> {
}