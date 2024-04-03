package com.example.demo.Controllers;

import com.example.demo.Services.GlossairesService;
import com.example.demo.entity.Glossaires;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/glossaires")
public class GlossairesController {

    private final GlossairesService glossairesService;

    @GetMapping("/all")
    public List<Glossaires> getAllGlossaires() {
        return glossairesService.getAllGlossaires();
    }

    @GetMapping("/{id}")
    public Glossaires getGlossairesById(@PathVariable("id") Long id) {
        return glossairesService.getGlossairesById(id);
    }

    @PostMapping("/add")
    public Glossaires createGlossaires(@RequestBody Glossaires glossaires) {
        Long datasetId = glossaires.getDataset().getId(); // Obtenir l'ID du dataset à partir de la glossaire
        return glossairesService.createGlossaires(glossaires, datasetId);
    }


    @PutMapping("/update/{id}")
    public Glossaires updateGlossaires(@PathVariable("id") Long id, @RequestBody Glossaires updatedGlossaires) {
        return glossairesService.updateGlossaires(id, updatedGlossaires);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGlossaires(@PathVariable("id") Long id) {
        glossairesService.deleteGlossaires(id);
    }
}
