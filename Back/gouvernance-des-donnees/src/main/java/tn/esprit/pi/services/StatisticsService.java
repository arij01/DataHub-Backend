package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Quality;
import tn.esprit.pi.entities.Statistics;
import tn.esprit.pi.repositories.QualityRepository;
import tn.esprit.pi.repositories.StatisticsRepository;

import java.util.List;
@Service
public class StatisticsService implements IStatisticsService{
    @Autowired
    StatisticsRepository statisticsRepository;
    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }
    @Override
    public List<Statistics> getAllStatistics() {
        return statisticsRepository.findAll();
    }
    @Override
    public Statistics saveStatistics(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }
    @Override
    public void deleteStatistics(long id) {
        statisticsRepository.deleteById(id);
    }
    @Override
    public void updateStatistics(Statistics s) {
        Statistics savedS = statisticsRepository.findById(s.getId())
                .orElseThrow(() -> new RuntimeException(String.format("cannot find Statistics by ID %d", s.getId())));
        savedS.setColumnName(s.getColumnName());
        savedS.setAverage(s.getAverage());
        savedS.setMissingValues(s.getMissingValues());
        statisticsRepository.save(savedS);
    }
}
