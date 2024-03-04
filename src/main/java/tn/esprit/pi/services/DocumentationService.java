package tn.esprit.pi.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Documentation;
import tn.esprit.pi.repositories.DocumentationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentationService {
    @Autowired
    private DocumentationRepository documentationRepository;

    public List<Documentation> getAllDocumentations() {
        return documentationRepository.findAll();
    }

    public Documentation saveDocumentation(Documentation documentation) {
        return documentationRepository.save(documentation);
    }

    public Documentation getDocumentationById(String id) {
        return documentationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Documentation not found with id: " + id));
    }

//    public void updateDocumentation(Documentation documentation) {
//        Optional<Documentation> existingDocumentation = documentationRepository.findById(documentation.getId());
//        if (existingDocumentation.isPresent()) {
//            Documentation doc = existingDocumentation.get();
//            doc.setText(documentation.getText());
//            documentationRepository.save(doc);
//        } else {
//            throw new EntityNotFoundException("Documentation not found with id: " + documentation.getId());
//        }
//    }
    public Documentation updateDocById(String id, Documentation documentation) {
        return documentationRepository.findById(id)
                .map(existingDocumentation -> {
                    existingDocumentation.setTitle(documentation.getTitle());
                    existingDocumentation.setCreatedAt(documentation.getCreatedAt());
                    existingDocumentation.setLastUpdated(documentation.getLastUpdated());
                    existingDocumentation.setText(documentation.getText());
                    return documentationRepository.save(existingDocumentation);
                })
                .orElseThrow(() -> new RuntimeException("Documentation not found with id " + id));
    }
    public void deleteDocumentation(String id) {
        documentationRepository.deleteById(id);
    }


}