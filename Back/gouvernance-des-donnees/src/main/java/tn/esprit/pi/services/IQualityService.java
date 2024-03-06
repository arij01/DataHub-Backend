package tn.esprit.pi.services;

import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Quality;

import java.util.List;
@Service
public interface IQualityService {
    public List<Quality> getAllQuality();
    public Quality saveQuality(Quality quality);
    public void deleteQuality(long id);
    public void updateQuality(Quality c) ;

    }
