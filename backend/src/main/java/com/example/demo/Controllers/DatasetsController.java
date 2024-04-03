package com.example.demo.Controllers;

import com.example.demo.Services.DatasetsService;
import com.example.demo.Services.FeedbacksService;
import com.example.demo.entity.Datasets;
import com.example.demo.entity.Feedbacks;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/datasets")
public class DatasetsController {

    private final DatasetsService datasetsService;
    private final FeedbacksService feedbacksService;
    @PostMapping("/{id}/file")
    public ResponseEntity<Datasets> addFileToDataset(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Datasets datasets = datasetsService.addFileToDataset(id, file);
            return new ResponseEntity<>(datasets, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<Datasets> getAllDatasets() {
        return datasetsService.getAllDatasets();
    }

    @GetMapping("/{id}")
    public Datasets getDatasetById(@PathVariable("id") Long id) {
        return datasetsService.getDatasetById(id);
    }

    @PostMapping("/add")
    public Datasets addDataset(@RequestBody Datasets dataset) {
        return datasetsService.createDataset(dataset);
    }

    @PutMapping("/update/{id}")
    public Datasets updateDataset(@PathVariable("id") Long id, @RequestBody Datasets updatedDataset) {
        return datasetsService.updateDataset(id, updatedDataset);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDataset(@PathVariable("id") Long id) {
        datasetsService.deleteDataset(id);
    }
    @GetMapping("/feedbacks/all")
    public List<Feedbacks> getAllFeedbacks() {
        return feedbacksService.getAllFeedbacks();
    }
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getDatasetNameById(@PathVariable Long id) {
        String datasetName = datasetsService.getDatasetNameById(id);
        if (datasetName != null) {
            return new ResponseEntity<>(datasetName, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{id}/image")
    public ResponseEntity<Datasets> addImageToDataset(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        try {
            Datasets dataset = datasetsService.addImageToDataset(id, image);
            return new ResponseEntity<>(dataset, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImageForDataset(@PathVariable Long id) {
        try {
            byte[] imageData = datasetsService.getImageForDataset(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
