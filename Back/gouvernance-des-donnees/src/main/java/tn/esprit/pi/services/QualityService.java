package tn.esprit.pi.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pi.entities.Quality;
import tn.esprit.pi.repositories.QualityRepository;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@Service
public class QualityService implements IQualityService{
    @Autowired
    QualityRepository qualityRepository;
    private List<String[]> readCSV(MultipartFile csvFilePath) throws IOException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(csvFilePath.getInputStream()))) {
            return reader.readAll();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    public void detectAndCorrectProblems(Long id) {
        Quality quality = qualityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Metadata not found with id: " + id));

        // Détection automatique des problèmes (exemple : valeurs manquantes)
        if (quality.getIncompleteValue() != null) {
            // Correction manuelle (exemple : utiliser une valeur par défaut)
            quality.setIncompleteValue("ValeurparDefaut");
            qualityRepository.save(quality);
        }
    }
    public void correctDataQValue(Long id, InputStream inputStream, String incompleteValue) {
        System.out.println("Processing request with id in service: " + id); // Ajoutez cette ligne
        Quality dataQ = qualityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DataQ not found with id: " + id));

        dataQ.setIncompleteValue("ValeurparDefaut");
        qualityRepository.save(dataQ);

    }
    public Map<String, Object> calculateQuality(Long id, MultipartFile csvFilePath) {
        Quality quality = qualityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quality not found with id: " + id));

        // Implémentez la logique pour calculer la qualité des données
        Map<String, Object> qualityData = new HashMap<>();

        double completeness = quality.getCompleteness() != null ? quality.getCompleteness() : 0;
        boolean accuracy = quality.getAccuracy() != null && quality.getAccuracy();
        String incompleteValue = quality.getIncompleteValue() != null ? quality.getIncompleteValue() : "";

        // Exemple de calcul de la qualité globale
        double overallQuality = calculateOverallQuality(completeness, accuracy);
        qualityData.put("overallQuality", overallQuality);

        // Exemple de calcul de la qualité de consistance
        double consistency = calculateConsistency(accuracy, incompleteValue);
        qualityData.put("consistency", consistency);

        // Exemple de calcul de la qualité d'opportunité
        double timeliness = calculateTimeliness();
        qualityData.put("timeliness", timeliness);

        // Ajout de la logique de calcul basée sur le fichier CSV
        try {
            List<String[]> csvData = readCSV(csvFilePath);
            // Exemple : ajuster les calculs en fonction des données du CSV
            adjustQualityCalculationsBasedOnCSV(csvData, qualityData);
        } catch (IOException e) {
            e.printStackTrace(); // Gérez les exceptions correctement selon vos besoins
        }

        // Ajoutez d'autres métriques et données au qualityData selon vos besoins

        return qualityData;
    }

    private double calculateOverallQuality(double completeness, boolean accuracy) {
        return (completeness + (accuracy ? 1 : 0)) / 2;
    }

    private double calculateConsistency(boolean accuracy, String incompleteValue) {
        return accuracy && incompleteValue.isEmpty() ? 1 : 0;
    }

    private double calculateTimeliness() {
        return 1;
    }

    private List<String[]> readCSV(String csvFilePath) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            return reader.readAll();
        }
    }
    private void adjustQualityCalculationsBasedOnCSV(List<String[]> csvData, Map<String, Object> qualityData) {
        for (int i = 1; i < csvData.size(); i++) {
            String[] row = csvData.get(i);

            // Assurez-vous que vous traitez correctement chaque colonne
            for (int j = 0; j < row.length; j++) {
                String cellValue = row[j].trim(); // Nettoyez la valeur en enlevant les espaces

                try {
                    double numericValue = Double.parseDouble(cellValue);
                    // Faites quelque chose avec la valeur numérique
                } catch (NumberFormatException e) {
                    // La valeur n'est pas un nombre, gérez cela selon vos besoins
                }
            }
        }
    }
    public Quality getDataQ(Long id) {
        return qualityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DataQ not found with id: " + id));
    }
    public QualityService(QualityRepository qualityRepository) {
        this.qualityRepository = qualityRepository;
    }
    @Override
    public List<Quality> getAllQuality() {
        return qualityRepository.findAll();
    }
    @Override
    public Quality saveQuality(Quality quality) {
        return qualityRepository.save(quality);
    }
    @Override
    public void deleteQuality(long id) {
        qualityRepository.deleteById(id);
    }

    @Override
    public void updateQuality(Quality c) {

    }
    /*@Override
    public void updateQuality(Quality c) {
        Quality savedQ = qualityRepository.findById(c.getId())
                .orElseThrow(() -> new RuntimeException(String.format("cannot find Quality by ID %d", c.getId())));
        savedQ.setNomChamp(c.getNomChamp());
        savedQ.setTypeChamp(c.getTypeChamp());
        savedQ.setFrequence(c.getFrequence());
        savedQ.setDistribution(c.getDistribution());
        savedQ.setDateDerniereMiseAJour(c.getDateDerniereMiseAJour());
        qualityRepository.save(savedQ);
    }*/





}
