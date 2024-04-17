package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.DataEntity;

@Repository
public interface DataEntityRepository extends JpaRepository<DataEntity, Long> {
}