package tn.esprit.pi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Solution;
@Repository
public interface SolutionRepository extends MongoRepository<Solution,String> {
}
