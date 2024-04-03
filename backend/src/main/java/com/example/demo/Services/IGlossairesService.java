package com.example.demo.Services;

import com.example.demo.entity.Glossaires;

import java.util.List;

public interface IGlossairesService {
    List<Glossaires> getAllGlossaires();
    Glossaires getGlossairesById(Long id);
    Glossaires createGlossaires(Glossaires glossaires, Long datasetId); // Modified method to accept dataset ID
    void deleteGlossaires(Long id);
    Glossaires updateGlossaires(Long id, Glossaires updatedGlossaires);
}
