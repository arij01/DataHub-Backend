/*
package tn.esprit.pi.services;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pi.entities.MetaDataExtractor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service

public class FileProcessingService {

    // Your existing methods for extracting metadata
    private Map<String, String> extractCsvMetadata(InputStream fileInputStream) throws IOException, CsvValidationException {
        // Implement CSV metadata extraction logic
        // Example: Read CSV file, extract metadata, and return a map
        return MetaDataExtractor.extractCsvMetadata(fileInputStream);
    }

    private Map<String, String> extractExcelMetadata(InputStream fileInputStream) throws IOException {
        // Implement Excel metadata extraction logic
        // Example: Read Excel file, extract metadata, and return a map
        return MetaDataExtractor.extractExcelMetadata(fileInputStream);
    }

    private Map<String, String> extractImageMetadata(InputStream fileInputStream) throws IOException {
        // Implement image metadata extraction logic
        // Example: Read image file, extract metadata, and return a map
        return MetaDataExtractor.extractImageMetadata(fileInputStream);
    }

    private Map<String, String> extractVideoMetadata(InputStream fileInputStream) throws IOException {
        // Implement video metadata extraction logic
        // Example: Read video file, extract metadata, and return a map
        return MetaDataExtractor.extractVideoMetadata(fileInputStream);
    }

    private Map<String, String> extractPdfMetadata(InputStream fileInputStream) throws IOException {
        // Implement PDF metadata extraction logic
        // Example: Read PDF file, extract metadata, and return a map
        return MetaDataExtractor.extractPdfMetadata(fileInputStream);
    }

    public Map<String, String> processFile(MultipartFile file) throws IOException, CsvValidationException {
        Map<String, String> metadata = null;

        // Determine file type based on file extension or other criteria
        String fileType = determineFileType(file.getOriginalFilename());

        // Perform processing based on file type
        switch (fileType.toUpperCase()) {
            case "CSV":
                metadata = extractCsvMetadata(file.getInputStream());
                break;
            case "EXCEL":
                metadata = extractExcelMetadata(file.getInputStream());
                break;
            case "IMAGE":
                metadata = extractImageMetadata(file.getInputStream());
                break;
            case "VIDEO":
                metadata = extractVideoMetadata(file.getInputStream());
                break;
            case "PDF":
                metadata = extractPdfMetadata(file.getInputStream());
                break;
            default:
                throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }

        // Additional processing logic if needed

        return metadata;
    }

    private String determineFileType(String fileName) {
        // Implement logic to determine file type based on file name or content
        // This is a simple example, you might want to use a more robust approach
        if (fileName.endsWith(".csv")) {
            return "CSV";
        } else if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
            return "EXCEL";
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
            return "IMAGE";
        } else if (fileName.endsWith(".mp4") || fileName.endsWith(".avi")) {
            return "VIDEO";
        } else if (fileName.endsWith(".pdf")) {
            return "PDF";
        } else {
            throw new IllegalArgumentException("Unknown file type for file: " + fileName);
        }
    }
}
*/
