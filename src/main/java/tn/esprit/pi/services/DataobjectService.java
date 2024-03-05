package tn.esprit.pi.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Dataobject;
import tn.esprit.pi.repositories.DataobjectRepository;

import java.util.List;

@Service
public class DataobjectService {
    @Autowired
    private DataobjectRepository dataobjectRepository;

    public List<Dataobject> getAllDataobjects() {
        return dataobjectRepository.findAll();
    }

    public Dataobject saveDataobject(Dataobject dataobject) {
        return dataobjectRepository.save(dataobject);
    }

    public Dataobject getDataobjectById(String id) {
        return dataobjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dataobject not found with id: " + id));
    }

    //    public void updateDataobject(Dataobject dataobject) {
//        Optional<Dataobject> existingDataobject = dataobjectRepository.findById(dataobject.getId());
//        if (existingDataobject.isPresent()) {
//            Dataobject doc = existingDataobject.get();
//            doc.setDataFormat(dataobject.getDataFormat());
//            dataobjectRepository.save(doc);
//        } else {
//            throw new EntityNotFoundException("Dataobject not found with id: " + dataobject.getId());
//        }
//    }
    public Dataobject updateDocById(String id, Dataobject dataobject) {
        return dataobjectRepository.findById(id)
                .map(existingDataobject -> {
                    existingDataobject.setUser(dataobject.getUser());
                    existingDataobject.setDescription(dataobject.getDescription());
                    existingDataobject.setDataType(dataobject.getDataType());
                    existingDataobject.setDataFormat(dataobject.getDataFormat());
                    return dataobjectRepository.save(existingDataobject);
                })
                .orElseThrow(() -> new RuntimeException("Dataobject not found with id " + id));
    }
    public void deleteDataobject(String id) {
        dataobjectRepository.deleteById(id);
    }
}

