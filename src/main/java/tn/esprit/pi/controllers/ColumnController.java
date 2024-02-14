package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Column;
import tn.esprit.pi.services.IColumnService;

import java.util.List;

@RestController
@RequestMapping("/api/columns")
public class ColumnController {
    @Autowired
    IColumnService columnService;
    @GetMapping
    public List<Column> getAllColumns() {
        return columnService.getAllColumns();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Column> getColumnById(@PathVariable String id) {
        Column column = columnService.getColumnById(id);
        if (column != null) {
            return new ResponseEntity<>(column, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Column saveColumn(@RequestBody Column column) {
        return columnService.saveColumn(column);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColumn(@PathVariable String id) {
        Column column = columnService.getColumnById(id);
        if (column != null) {
            columnService.deleteColumn(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping
    public ResponseEntity<Void> updateColumn(@RequestBody Column c){
        columnService.updateColumn(c);
        return new  ResponseEntity<>(HttpStatus.OK);
    }


}


