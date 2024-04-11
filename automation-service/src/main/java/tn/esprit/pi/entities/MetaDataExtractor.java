package tn.esprit.pi.entities;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.mp4parser.IsoFile;
import org.mp4parser.boxes.iso14496.part12.MovieBox;
import org.mp4parser.boxes.iso14496.part12.MovieHeaderBox;
import org.mp4parser.boxes.iso14496.part12.TrackBox;
import org.mp4parser.boxes.iso14496.part12.TrackHeaderBox;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MetaDataExtractor {
    public static Map<String, String> extractMetadata(InputStream fileInputStream, String fileType) throws IOException, CsvValidationException {

        if ("CSV".equals(fileType)) {
            return extractCsvMetadata(fileInputStream);
      } else if ("Excel".equals(fileType)) {
            return extractExcelMetadata(fileInputStream);
      } else if ("Video".equals(fileType)) {
            return extractVideoMetadata(fileInputStream);
        }  else if ("Image".equals(fileType)) {
                return extractImageMetadata(fileInputStream);
        } else if ("PDF".equals(fileType)) {
            return extractPdfMetadata(fileInputStream);
        } else {
            throw new IllegalArgumentException("Type de fichier inconnu : " + fileType);
        }
    }


      public static Map<String, String> extractCsvMetadata(InputStream fileInputStream) throws IOException, CsvValidationException {
          Map<String, String> metadata = new HashMap<>();
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String currentDate = dateFormat.format(new Date());
          metadata.put("extractionDate", currentDate);

          try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))) {
              CSVReader csvReader = new CSVReaderBuilder(reader).build();


              String[] columnNames = csvReader.readNext();
              if (columnNames != null) {
                  metadata.put("columnNames", String.join(", ", columnNames));
              }

              int rowCount = 0;
              while (csvReader.readNext() != null) {
                  rowCount++;
              }
              metadata.put("rowCount", String.valueOf(rowCount));
          }

          return metadata;
      }
    public static Map<String, String> extractExcelMetadata(InputStream fileInputStream) throws IOException {
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

    public static Map<String, String> extractImageMetadata(InputStream fileInputStream) {
        Map<String, String> metadata = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        metadata.put("extractionDate", currentDate);

        try {
            BufferedImage bufferedImage = ImageIO.read(fileInputStream);

            if (bufferedImage != null) {
                int width = bufferedImage.getWidth();
                int height = bufferedImage.getHeight();
                metadata.put("width", String.valueOf(width));
                metadata.put("height", String.valueOf(height));


            }

        } catch (IOException e) {
            e.printStackTrace();

        }

        return metadata;
    }
    public static Map<String, String> extractVideoMetadata(InputStream fileInputStream) {
        Map<String, String> metadata = new HashMap<>();

        try {
            IsoFile isoFile = new IsoFile(String.valueOf(fileInputStream));
            MovieBox movieBox = isoFile.getBoxes(MovieBox.class).get(0);
            MovieHeaderBox movieHeaderBox = movieBox.getBoxes(MovieHeaderBox.class).get(0);

            metadata.put("Duration (seconds)", String.valueOf(movieHeaderBox.getDuration() / movieHeaderBox.getTimescale()));


            List<TrackBox> trackBoxes = movieBox.getBoxes(TrackBox.class);
            for (int i = 0; i < trackBoxes.size(); i++) {
                TrackBox trackBox = trackBoxes.get(i);
                TrackHeaderBox trackHeaderBox = trackBox.getBoxes(TrackHeaderBox.class).get(0);
                metadata.put("Track " + (i + 1) + " Duration (seconds)", String.valueOf(trackHeaderBox.getDuration() / trackHeaderBox.getTrackId()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return metadata;
    }

    public static Map<String, String> extractPdfMetadata(InputStream fileInputStream) throws IOException {
        Map<String, String> metadata = new HashMap<>();

        try (PDDocument document = PDDocument.load(fileInputStream)) {
            PDDocumentInformation documentInformation = document.getDocumentInformation();

            metadata.put("title", documentInformation.getTitle());
            metadata.put("author", documentInformation.getAuthor());
            metadata.put("subject", documentInformation.getSubject());
            metadata.put("keywords", documentInformation.getKeywords());
            metadata.put("creator", documentInformation.getCreator());
            metadata.put("numberOfPages", String.valueOf(document.getNumberOfPages()));
        }

        return metadata;
    }












 /*   public static Map<String, String> extractVideoMetadata(InputStream fileInputStream) {
        Map<String, String> metadata = new HashMap<>();

        try {
            IsoFile isoFile = new IsoFile(String.valueOf(fileInputStream));

            MovieBox movieBox = isoFile.getBoxes(MovieBox.class).get(0);
            MovieHeaderBox movieHeaderBox = movieBox.getBoxes(MovieHeaderBox.class).get(0);

            // Basic information
            metadata.put("Duration (seconds)", String.valueOf(movieHeaderBox.getDuration() / movieHeaderBox.getTimescale()));
            metadata.put("Creation Time", movieHeaderBox.getCreationTime().toString());

            // Video track information
            TrackBox videoTrack = isoFile.getBoxes(TrackBox.class).stream()
                    .filter(trackBox -> "vide".equals(trackBox.getMediaBox().getHandlerBox()))
                    .findFirst().orElse(null);

            if (videoTrack != null) {
                TrackHeaderBox trackHeaderBox = videoTrack.getBoxes(TrackHeaderBox.class).get(0);

                metadata.put("Video Track Duration (seconds)", String.valueOf(trackHeaderBox.getDuration() / trackHeaderBox.getDuration()));
                metadata.put("Video Track Width", String.valueOf(trackHeaderBox.getWidth()));
                metadata.put("Video Track Height", String.valueOf(trackHeaderBox.getHeight()));
            }

            // Audio track information
            TrackBox audioTrack = isoFile.getBoxes(TrackBox.class).stream()
                    .filter(trackBox -> "soun".equals(trackBox.getMediaBox().getHandlerBox()))
                    .findFirst().orElse(null);

            if (audioTrack != null) {
                TrackHeaderBox trackHeaderBox = audioTrack.getBoxes(TrackHeaderBox.class).get(0);

                metadata.put("Audio Track Duration (seconds)", String.valueOf(trackHeaderBox.getDuration() / trackHeaderBox.getDuration()));
                metadata.put("Audio Sample Rate", String.valueOf(trackHeaderBox.getLayer()));

            }

            // Add more metadata fields as needed

        } catch (Exception e) {
            // Handle the exception according to your needs
            e.printStackTrace();
        }

        return metadata;
    }*/

  /*  public static Map<String, String> extractImageMetadata(InputStream fileInputStream) throws IOException {
        Map<String, String> metadata = new HashMap<>();

        try {
            // Ajouter la logique pour extraire les métadonnées spécifiques de l'image
            Metadata imageMetadata = ImageMetadataReader.readMetadata(fileInputStream);

            // Exemple d'extraction d'informations EXIF
            ExifIFD0Directory exifIFD0Directory = imageMetadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (exifIFD0Directory != null) {
                String make = exifIFD0Directory.getString(ExifIFD0Directory.TAG_MAKE);
                String model = exifIFD0Directory.getString(ExifIFD0Directory.TAG_MODEL);
                metadata.put("make", make);
                metadata.put("model", model);

            }


            IptcDirectory iptcDirectory = imageMetadata.getFirstDirectoryOfType(IptcDirectory.class);
            if (iptcDirectory != null) {
                String author = iptcDirectory.getString(IptcDirectory.TAG_BY_LINE);
                String description = iptcDirectory.getString(IptcDirectory.TAG_CAPTION);
              ;
                metadata.put("author", author);
                metadata.put("description", description);
                metadata.put("Image Width", imageMetadata.getFirstDirectoryOfType(ExifIFD0Directory.class).getString(ExifIFD0Directory.TAG_IMAGE_WIDTH));
                metadata.put("Image Height", imageMetadata.getFirstDirectoryOfType(ExifIFD0Directory.class).getString(ExifIFD0Directory.TAG_IMAGE_HEIGHT));
                // Ajoutez d'autres informations IPTC selon vos besoins
            }

        } catch (IOException e) {
            // Gérer l'exception selon les besoins
            e.printStackTrace();
        } catch (ImageProcessingException e) {
            throw new RuntimeException(e);
        }

        return metadata;
    }*/

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


}











