package tn.esprit.pi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pi.entities.Column;
import tn.esprit.pi.entities.FileMetaData;
import tn.esprit.pi.services.MetaDataService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Automation")
public class FileContrloller {
/*
    @Autowired
    public JobLauncher jobLauncher;

    @Autowired
    public Job myJob;

    @Autowired
    public BatchConfig batchConfig;
*/
    @Autowired
        public MetaDataService metadataService;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        metadataService.processFile(file);
        return "File uploaded successfully!";
    }

    @GetMapping("/get")
    public List<Column> getColumns(){
        return metadataService.getColumns();
    }

/*    @PostMapping("/uploadBatch")
     public ResponseEntity<String> handleFileUploadB(@RequestParam("file") MultipartFile file) {

        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .addString("fileName", file.getOriginalFilename())
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(batchConfig.metadataProcessingJob, jobParameters);

            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                return ResponseEntity.ok("File uploaded and batch processing completed successfully!");
            } else {
                return ResponseEntity.ok("File uploaded, but batch processing failed with status: " + jobExecution.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the file: " + e.getMessage());
        }
    }*/
}
