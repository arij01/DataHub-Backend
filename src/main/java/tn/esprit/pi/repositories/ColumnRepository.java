package tn.esprit.pi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.Column;
import tn.esprit.pi.entities.Label;

import java.util.List;

@Repository
public interface ColumnRepository extends MongoRepository<Column,String> {
    List<Column> findByLabel(Label label);

}
