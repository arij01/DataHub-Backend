package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Documentation;
import tn.esprit.pi.services.DocumentationService;

import java.util.List;

@RestController
@RequestMapping("/api/documentations")
public class DocumentationController {
    @Autowired
    private DocumentationService documentationService;

    @GetMapping
    public List<Documentation> getAllDocumentations() {
        return documentationService.getAllDocumentations();
    }

    @PostMapping
    public Documentation saveDocumentation(@RequestBody Documentation documentation) {
        return documentationService.saveDocumentation(documentation);
    }

    @GetMapping("/{id}")
    public Documentation getDocumentationById(@PathVariable String id) {
        return documentationService.getDocumentationById(id);
    }

    @PutMapping
    public void updateDocumentation(@RequestBody Documentation documentation) {
        documentationService.updateDocumentation(documentation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocumentation(@PathVariable String id) {
        documentationService.deleteDocumentation(id);
        return ResponseEntity.ok().build();
    }
}