package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Column saveComponent(@RequestBody Column column) {
        return columnService.saveComponent(column);
    }

}
