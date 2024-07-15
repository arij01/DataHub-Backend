package com.example.demo.service;
import com.example.demo.entity.TraceabilityEntry;
import com.example.demo.repository.TraceabilityEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TraceabilityEntryServiceImpl implements TraceabilityEntryService {

    private final TraceabilityEntryRepository traceabilityEntryRepository;

    @Autowired
    public TraceabilityEntryServiceImpl(TraceabilityEntryRepository traceabilityEntryRepository) {
        this.traceabilityEntryRepository = traceabilityEntryRepository;
    }

    @Override
    public TraceabilityEntry saveTraceabilityEntry(TraceabilityEntry traceabilityEntry) {
        return traceabilityEntryRepository.save(traceabilityEntry);
    }

    @Override
    public List<TraceabilityEntry> getAllTraceabilityEntries() {
        return traceabilityEntryRepository.findAll();
    }
    @Override
    public TraceabilityEntry createTraceabilityEntry(TraceabilityEntry entry) {
        // Logique pour créer une nouvelle entrée de traceabilité et la sauvegarder dans le repository
        return traceabilityEntryRepository.save(entry);
    }
    @Override
    public Optional<TraceabilityEntry> updateTraceabilityEntry(Integer id, TraceabilityEntry entry) {
        // Logique pour mettre à jour une entrée de traceabilité avec l'identifiant spécifié
        // Assurez-vous d'utiliser id pour rechercher l'entrée existante dans le repository
        // et de mettre à jour ses propriétés avec les valeurs de l'objet entry.
        // Puis, sauvegardez l'entrée mise à jour dans le repository.

        TraceabilityEntry updatedEntry = traceabilityEntryRepository.save(entry);
        return Optional.of(updatedEntry);
    }

    @Override
    public Optional<TraceabilityEntry> getTraceabilityEntryById(Integer id) {
        return traceabilityEntryRepository.findById(id);
    }

    @Override
    public void deleteTraceabilityEntry(Integer id) {
        traceabilityEntryRepository.deleteById(id);
    }
}

