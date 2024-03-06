package tn.esprit.pi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pi.entities.Quality;
import tn.esprit.pi.entities.Statistics;
import tn.esprit.pi.services.IQualityService;
import tn.esprit.pi.services.IStatisticsService;
import tn.esprit.pi.services.QualityService;
import org.springframework.http.HttpStatus;
import tn.esprit.pi.services.StatisticsService;


import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

    @Autowired
    IStatisticsService iStatisticsService;

    @GetMapping("/retrieve-all-stat")
    public List<Statistics> getStatistics() {
        return iStatisticsService.getAllStatistics();
    }

    @PostMapping("/add")
    public Statistics addStatistics(@RequestBody Statistics p) {
        return iStatisticsService.saveStatistics(p);
    }

    // http://localhost:8089/exam/projet/remove-projet/{projet-id}
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") Long Id) {
        iStatisticsService.deleteStatistics(Id);
    }

    // http://localhost:8089/exam/projet/modify-projet
    @PutMapping("/modify")
    public Statistics modify(@RequestBody Statistics p) {
        iStatisticsService.updateStatistics(p);
        return p;
    }


}
