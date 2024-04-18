/*
package tn.esprit.pi.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pi.services.MetaDataService;

import java.util.List;
@Component
public class FileProcessingSchedular {

    private final MetaDataService metaDataService;

    public FileProcessingSchedular(MetaDataService metaDataService) {
        this.metaDataService = metaDataService;
    }
    @Scheduled(fixedRate = 10000)
    public void processAndUploadFile() {
        List<MultipartFile> processedFiles = metaDataService.getProcessedFiles();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        for (MultipartFile processedFile : processedFiles) {
            metaDataService.processFile(processedFile);
        }


    }
}
*/
