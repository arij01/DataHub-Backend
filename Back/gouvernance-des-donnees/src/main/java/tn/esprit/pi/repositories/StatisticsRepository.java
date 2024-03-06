package tn.esprit.pi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.pi.entities.Statistics;

public interface StatisticsRepository extends MongoRepository<Statistics,Long> {
}
