package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pi.entities.Solution;
import tn.esprit.pi.services.SolutionService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/solutions")
public class SolutionController {
    @Autowired
    private SolutionService solutionService;

    @GetMapping
    public ResponseEntity<List<Solution>> getAllSolutions() {
        List<Solution> solutions = solutionService.getAllSolutions();
        return ResponseEntity.ok(solutions);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSolution(@RequestParam("fournisseur") String fournisseur,
                                            @RequestParam("typecout") String typecout,
                                            @RequestParam("typesolution") String typesolution,
                                            @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Veuillez sélectionner un fichier à télécharger.");
        }
        try {
            Solution solution = solutionService.createSolution(fournisseur, typecout, typesolution, file);
            Solution savedSolution = solutionService.saveSolution(solution);
            return ResponseEntity.ok(savedSolution);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de la création de la solution.");
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Solution> getSolutionById(@PathVariable String id) {
        Solution solution = solutionService.getSolutionById(id);
        if (solution != null) {
            return ResponseEntity.ok(solution);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Solution> updateSolutionById(@PathVariable String id, @RequestBody Solution solution) {
        Solution updatedSolution = solutionService.updateSolutionById(id, solution);
        if (updatedSolution != null) {
            return ResponseEntity.ok(updatedSolution);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSolution(@PathVariable String id) {
        Solution solution = solutionService.getSolutionById(id);
        if (solution != null) {
            solutionService.deleteSolution(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}