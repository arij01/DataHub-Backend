package tn.esprit.pi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pi.entities.Quality;
import tn.esprit.pi.services.IQualityService;
import tn.esprit.pi.services.QualityService;
import org.springframework.http.HttpStatus;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quality")
public class QualityController {

    @Autowired
    QualityService qualityService;
    @GetMapping("/{id}/quality")
    public ResponseEntity<Map<String, Object>> getDataQQuality(@PathVariable Long id, @RequestParam("csvFile") MultipartFile csvFile) {
        Map<String, Object> dataQuality = qualityService.calculateQuality(id,csvFile);
        return ResponseEntity.ok(dataQuality);
    }
    @PostMapping("/{id}/correct")
    public ResponseEntity<Void> correctDataQValue(
            @PathVariable Long id,
            @RequestParam("csvFile") MultipartFile csvFile,
            @RequestParam("incompleteValue") String incompleteValue
    ) throws IOException {
        qualityService.correctDataQValue(id,csvFile.getInputStream(), incompleteValue);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/retrieve-all-qualities")
    public List<Quality> getQualities() {
        return qualityService.getAllQuality();
    }

    @PostMapping("/add")
    public Quality addQuality(@RequestBody Quality p) {
        return qualityService.saveQuality(p);
    }

    // http://localhost:8089/exam/projet/remove-projet/{projet-id}
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") Long Id) {
        qualityService.deleteQuality(Id);
    }

    // http://localhost:8089/exam/projet/modify-projet
    @PutMapping("/modify")
    public Quality modify(@RequestBody Quality p) {
        qualityService.updateQuality(p);
        return p;
    }


}
