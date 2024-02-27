package tn.esprit.pi.services;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pi.entities.FileMetaData;
import tn.esprit.pi.entities.MetaDataExtractor;

import java.io.IOException;
import java.util.Map;

@Service
public class MetaDataService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void processFile(MultipartFile file) {
        try {


            Map<String, String> metadata = MetaDataExtractor.extractMetadata(file.getInputStream(), getFileType(file.getOriginalFilename()));

            FileMetaData fileMetadata = new FileMetaData();
            fileMetadata.setFileName(file.getOriginalFilename());
            fileMetadata.setFileType(getFileType(file.getOriginalFilename()));
            fileMetadata.setMetadata(metadata);

            mongoTemplate.save(fileMetadata);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }





    private String getFileType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        switch (extension) {
            case "csv":
                return "CSV";
            case "xls":
            case "xlsx":
                return "Excel";
            case "pdf":
                return "PDF";
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
                return "Image";
            case "mp4":
                return "Video";
            default:
                return "Unknown";
        }
    }
}
