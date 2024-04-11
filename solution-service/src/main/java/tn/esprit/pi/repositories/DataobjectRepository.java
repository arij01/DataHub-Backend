package tn.esprit.pi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Dataobject;
@Repository
public interface DataobjectRepository extends MongoRepository<Dataobject,String> {
}
