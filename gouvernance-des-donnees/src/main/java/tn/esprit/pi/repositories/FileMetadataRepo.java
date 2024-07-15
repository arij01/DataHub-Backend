package tn.esprit.pi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pi.entities.FileMetaData;

@Repository
public interface FileMetadataRepo extends MongoRepository<FileMetaData, String> {
}
