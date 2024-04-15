package tn.esprit.pi.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Column;
import tn.esprit.pi.entities.Label;
import tn.esprit.pi.repositories.ColumnRepository;

import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
@Slf4j
public class ColumnService {
    @Autowired
    private ColumnRepository columnRepository;

    @Scheduled(cron = "0 * * * * *")
    public List<Column> getAllColumns() {
        log.info("Retrieving all columns...");
        List<Column> columns = columnRepository.findAll();
        log.info("Retrieved {} columns.", columns.size());
        for (Column c : columns) {
            log.info("Details de column: {}", c);
        }

        return columns;
    }


    public Column getColumnById(String id) {
        Optional<Column> column = columnRepository.findById(id);
        return column.orElse(null);
    }



    public Column saveColumn(Column c) {
        return columnRepository.save(c);
    }


    public void deleteColumn(String id) {
        columnRepository.deleteById(id);
    }
//    @Override
//    public void updateColumn(Column c) {
//        Column savedColumn = columnRepository.findById(c.getId())
//                .orElseThrow(() -> new RuntimeException(String.format("cannot find column by ID %d", c.getId())));
//        savedColumn.setNom(c.getNom());
//        savedColumn.setSynonyme(c.getSynonyme());
//        savedColumn.setType(c.getType());
//        savedColumn.setLabel(c.getLabel());
//        savedColumn.setBusinessKey(c.getBusinessKey());
//        columnRepository.save(savedColumn);
//    }

    public Column updateColumnById(String id, Column column) {
        return columnRepository.findById(id)
                .map(existingColumn -> {
                    existingColumn.setNom(column.getNom());
                    existingColumn.setSynonyme(column.getSynonyme());
                    existingColumn.setType(column.getType());
                    existingColumn.setLabel(column.getLabel());
                    existingColumn.setBusinessKey(column.getBusinessKey());
                    existingColumn.setChampResultant(column.getChampResultant());
                    existingColumn.setDocumentation(column.getDocumentation());
                    return columnRepository.save(existingColumn);
                })
                .orElseThrow(() -> new RuntimeException("Column not found with id " + id));
    }
    public List<Column> findColumnsByLabel(Label label) {
        return columnRepository.findByLabel(label);
    }



}
