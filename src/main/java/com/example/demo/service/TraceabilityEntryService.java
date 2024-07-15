package com.example.demo.service;

import com.example.demo.entity.TraceabilityEntry;

import java.util.List;
import java.util.Optional;

public interface TraceabilityEntryService {
    TraceabilityEntry saveTraceabilityEntry(TraceabilityEntry traceabilityEntry);
    List<TraceabilityEntry> getAllTraceabilityEntries();
    TraceabilityEntry createTraceabilityEntry(TraceabilityEntry entry);
    Optional<TraceabilityEntry> getTraceabilityEntryById(Integer id);
    Optional<TraceabilityEntry> updateTraceabilityEntry(Integer id, TraceabilityEntry entry);
    void deleteTraceabilityEntry(Integer id);
}
