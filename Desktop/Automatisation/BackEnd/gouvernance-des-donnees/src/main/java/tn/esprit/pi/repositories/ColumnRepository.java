package tn.esprit.pi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Column;

@Repository
public interface ColumnRepository extends MongoRepository<Column,String> {

}
