package tn.esprit.pi.entities;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class MetaDataExtractor {
    public static Map<String, String> extractMetadata(InputStream fileInputStream, String fileType) throws IOException, CsvValidationException {
        // Logique d'extraction des métadonnées en fonction du type de fichier (CSV, Excel, PDF)
        if ("CSV".equals(fileType)) {
            return extractCsvMetadata(fileInputStream);

        } else {
            throw new IllegalArgumentException("Type de fichier inconnu : " + fileType);
        }
    }


      private static Map<String, String> extractCsvMetadata(InputStream fileInputStream) throws IOException, CsvValidationException {
          Map<String, String> metadata = new HashMap<>();

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
     }











