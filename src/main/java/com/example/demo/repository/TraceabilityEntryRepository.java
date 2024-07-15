package com.example.demo.repository;

import com.example.demo.entity.TraceabilityEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraceabilityEntryRepository extends JpaRepository<TraceabilityEntry, Integer> {
}
