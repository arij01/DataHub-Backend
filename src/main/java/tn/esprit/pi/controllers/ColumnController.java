package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Column;
import tn.esprit.pi.entities.Documentation;
import tn.esprit.pi.entities.Label;
import tn.esprit.pi.repositories.DocumentationRepository;
import tn.esprit.pi.services.ColumnService;
import tn.esprit.pi.services.DocumentationService;

import java.util.List;

@RestController
@RequestMapping("/api/columns")
public class ColumnController {
    @Autowired
    private ColumnService columnService;
//    private DocumentationService documentationService;
//    private DocumentationRepository documentationRepository;
    @GetMapping
    public List<Column> getAllColumns() {
        return columnService.getAllColumns();
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Column> getColumnById(@PathVariable String id) {
        Column column = columnService.getColumnById(id);
        if (column != null) {
            return new ResponseEntity<>(column, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public Column saveColumn(@RequestBody Column column) {
        return columnService.saveColumn(column);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteColumn(@PathVariable String id) {
        Column column = columnService.getColumnById(id);
        if (column != null) {
            columnService.deleteColumn(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//    @PutMapping("/update")
//    public ResponseEntity<Void> updateColumn(@RequestBody Column c){
//        columnService.updateColumn(c);
//        return new  ResponseEntity<>(HttpStatus.OK);
//    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> updateColumnById(@PathVariable String id ,@RequestBody Column c){
        Column column = columnService.getColumnById(id);
        if (column != null) {
            columnService.updateColumnById(id,c);
            return new  ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search/{label}")
    public List<Column> searchColumnsByLabel(@PathVariable Label label) {
        return columnService.findColumnsByLabel(label);
    }
    @GetMapping("/all")
    public List<Column> getAllColumnsWithNames() {
        List<Column> columns = columnService.getAllColumns();
        columns.forEach(column -> column.setNom(column.getNom() != null ? column.getNom() : "(none)"));
        return columns;
    }
//    @GetMapping("/doc")
//    public Documentation getDocumentationByTitle(@RequestParam("title") String title) {
//        return documentationRepository.findByTitle(title);
//    }


}


