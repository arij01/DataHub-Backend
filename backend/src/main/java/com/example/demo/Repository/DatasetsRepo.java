package com.example.demo.Repository;

import com.example.demo.entity.Datasets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasetsRepo extends JpaRepository<Datasets,Long> {
    String findNomById(Long id);
}

