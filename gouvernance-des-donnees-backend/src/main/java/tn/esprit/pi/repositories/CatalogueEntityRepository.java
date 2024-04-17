package tn.esprit.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.CatalogueEntity;

@Repository
public interface CatalogueEntityRepository extends JpaRepository<CatalogueEntity, Long> {
}