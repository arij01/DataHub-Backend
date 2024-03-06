package tn.esprit.pi.services;

import tn.esprit.pi.entities.Quality;
import tn.esprit.pi.entities.Statistics;

import java.util.List;

public interface IStatisticsService {
    public List<Statistics> getAllStatistics();
    public Statistics saveStatistics(Statistics statistics);
    public void deleteStatistics(long id);
    public void updateStatistics(Statistics s) ;
}
