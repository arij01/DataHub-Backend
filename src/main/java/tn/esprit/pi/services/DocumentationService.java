package tn.esprit.pi.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Documentation;
import tn.esprit.pi.repositories.DocumentationRepository;

import java.util.List;

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

    public Documentation updateDocumentation(String id, Documentation documentation) {
        Documentation existingDocumentation = getDocumentationById(id);
        existingDocumentation.setText(documentation.getText());
        return documentationRepository.save(existingDocumentation);
    }

    public void deleteDocumentation(String id) {
        documentationRepository.deleteById(id);
    }
}