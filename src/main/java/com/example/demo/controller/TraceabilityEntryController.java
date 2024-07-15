package com.example.demo.controller;
import com.example.demo.entity.TraceabilityEntry;
import com.example.demo.service.TraceabilityEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/traceability")
public class TraceabilityEntryController {

    private final TraceabilityEntryService traceabilityEntryService;

    @Autowired
    public TraceabilityEntryController(TraceabilityEntryService traceabilityEntryService) {
        this.traceabilityEntryService = traceabilityEntryService;
    }

    @GetMapping("/entries")
    public ResponseEntity<List<TraceabilityEntry>> getAllTraceabilityEntries() {
        List<TraceabilityEntry> entries = traceabilityEntryService.getAllTraceabilityEntries();
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @GetMapping("/entries/{id}")
    public ResponseEntity<TraceabilityEntry> getTraceabilityEntryById(@PathVariable Integer id) {
        return traceabilityEntryService.getTraceabilityEntryById(id)
                .map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/entries")
    public ResponseEntity<TraceabilityEntry> createTraceabilityEntry(@RequestBody TraceabilityEntry entry) {
        TraceabilityEntry createdEntry = traceabilityEntryService.createTraceabilityEntry(entry);
        return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
    }

    @PutMapping("/entries/{id}")
    public ResponseEntity<TraceabilityEntry> updateTraceabilityEntry(@PathVariable Integer id, @RequestBody TraceabilityEntry entry) {
        return traceabilityEntryService.updateTraceabilityEntry(id, entry)
                .map(updatedEntry -> new ResponseEntity<>(updatedEntry, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/entries/{id}")
    public ResponseEntity<Void> deleteTraceabilityEntry(@PathVariable Integer id) {
        traceabilityEntryService.deleteTraceabilityEntry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
