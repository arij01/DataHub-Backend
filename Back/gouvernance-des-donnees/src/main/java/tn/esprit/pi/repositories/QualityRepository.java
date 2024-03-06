package tn.esprit.pi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Quality;

import java.util.List;

@Repository
public interface QualityRepository extends MongoRepository<Quality, Long> {
}