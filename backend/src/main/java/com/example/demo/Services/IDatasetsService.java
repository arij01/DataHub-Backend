package com.example.demo.Services;

import com.example.demo.entity.Datasets;

import java.util.List;

public interface IDatasetsService {
    List<Datasets> getAllDatasets();
    Datasets getDatasetById(Long id);
    Datasets createDataset(Datasets dataset);
    void deleteDataset(Long id);
    Datasets updateDataset(Long id, Datasets updatedDataset);
    public String getDatasetNameById(Long id);
}
