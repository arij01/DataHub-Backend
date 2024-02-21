package tn.esprit.pi.entities;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;




public class MetaDataExtractor {
    public static Map<String, String> extractMetadata(InputStream fileInputStream, String fileType) throws IOException, CsvValidationException {

        if ("CSV".equals(fileType)) {
            return extractCsvMetadata(fileInputStream);
      } else if ("Excel".equals(fileType)) {
            return extractExcelMetadata(fileInputStream);
    /*  } else if ("PDF".equals(fileType)) {
            return extractPdfMetadata(fileInputStream);*/
        } else {
            throw new IllegalArgumentException("Type de fichier inconnu : " + fileType);
        }
    }


      private static Map<String, String> extractCsvMetadata(InputStream fileInputStream) throws IOException, CsvValidationException {
          Map<String, String> metadata = new HashMap<>();
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String currentDate = dateFormat.format(new Date());
          metadata.put("extractionDate", currentDate);

          try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))) {
              CSVReader csvReader = new CSVReaderBuilder(reader).build();

              // Lire la première ligne pour obtenir les noms de colonnes
              String[] columnNames = csvReader.readNext();
              if (columnNames != null) {
                  metadata.put("columnNames", String.join(", ", columnNames));
              }

              // Lire le reste du fichier pour obtenir le nombre de lignes
              int rowCount = 0;
              while (csvReader.readNext() != null) {
                  rowCount++;
              }
              metadata.put("rowCount", String.valueOf(rowCount));
          }

          return metadata;
      }
    private static Map<String, String> extractExcelMetadata(InputStream fileInputStream) throws IOException {
        Map<String, String> metadata = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        metadata.put("extractionDate", currentDate);

        try ( Workbook workbook = WorkbookFactory.create(fileInputStream)) {
            int numberOfSheets = workbook.getNumberOfSheets();
            metadata.put("numberOfSheets", String.valueOf(numberOfSheets));

            int totalRowCount = 0;
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                int rowCount = sheet.getPhysicalNumberOfRows();
                totalRowCount += rowCount;
            }
            metadata.put("rowCount", String.valueOf(totalRowCount));
        }

        return metadata;

    }

/*   private static Map<String, String> extractExcelMetadata(InputStream fileInputStream) throws IOException {
       Map<String, String> metadata = new HashMap<>();

       try {
            parser = new OOXMLParser();
           BodyContentHandler handler = new BodyContentHandler();
           Metadata tikaMetadata = new Metadata();
           ParseContext context = new ParseContext();

           parser.parse(fileInputStream, handler, tikaMetadata, context);

           // Extract metadata
           String[] metadataNames = tikaMetadata.names();
           for (String name : metadataNames) {
               metadata.put(name, tikaMetadata.get(name));
           }
       } catch (Exception e) {
           throw new IOException("Error extracting Excel metadata", e);
       }

       return metadata;
   }*/

/*    public static Map<String, String> extractPdfMetadata(InputStream fileInputStream) throws IOException {
        Map<String, String> metadata = new HashMap<>();

        try (PDDocument document = PDDocument.load(fileInputStream)) {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String extractedText = pdfTextStripper.getText(document);

            // Add basic metadata for illustration
            metadata.put("extractedText", extractedText);
            metadata.put("wordCount", String.valueOf(extractedText.split("\\s+").length));
        }

        return metadata;
    }*/
/*public static Map<String, String> extractPdfMetadata(File pdfFile) throws IOException {
    Map<String, String> metadata = new HashMap<>();

    try {
        ITesseract tesseract = new Tesseract();
        String extractedText = tesseract.doOCR(pdfFile);

        metadata.put("extractedText", extractedText);
        metadata.put("wordCount", String.valueOf(extractedText.split("\\s+").length));
    } catch (TesseractException e) {
        // Handle TesseractException as needed
        e.printStackTrace();
    }

    return metadata;
}*/
/*public static Map<String, String> extractPdfMetadata(InputStream fileInputStream) throws IOException {
    Map<String, String> metadata = new HashMap<>();

    try (PDDocument document = PDDocument.load(fileInputStream)) {
        PDDocumentInformation documentInformation = document.getDocumentInformation();

        metadata.put("title", documentInformation.getTitle());
        metadata.put("author", documentInformation.getAuthor());
        metadata.put("subject", documentInformation.getSubject());
        metadata.put("keywords", documentInformation.getKeywords());
        metadata.put("creator", documentInformation.getCreator());
        metadata.put("producer", documentInformation.getProducer());
        metadata.put("createdDate", documentInformation.getCreationDate().toString());
        metadata.put("modifiedDate", documentInformation.getModificationDate().toString());
    }

    return metadata;
}*/

}











