package tn.esprit.pi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pi.services.MetaDataService;

@RestController
@RequestMapping("/Automation")
public class FileContrloller {
    @Autowired
    private MetaDataService metadataService;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        metadataService.processFile(file);
        return "File uploaded successfully!";
    }
}
