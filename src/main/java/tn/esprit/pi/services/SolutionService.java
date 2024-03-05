package tn.esprit.pi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.pi.entities.File;
import tn.esprit.pi.entities.Solution;
import tn.esprit.pi.entities.Typecout;
import tn.esprit.pi.entities.Typesolution;
import tn.esprit.pi.repositories.SolutionRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SolutionService {
    @Autowired
    private SolutionRepository solutionRepository;

    public List<Solution> getAllSolutions() {
        return solutionRepository.findAll();
    }

    public Solution getSolutionById(String id) {
        Optional<Solution> solution = solutionRepository.findById(id);
        return solution.orElse(null);
    }



    public Solution saveSolution(Solution c) {
        return solutionRepository.save(c);
    }
    public Solution createSolution(String fournisseur, String typecout, String typesolution, MultipartFile file) throws IOException {
        File fileEntity = new File();
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setSize(file.getSize());
        fileEntity.setType(file.getContentType());
        fileEntity.setBytes(file.getBytes());

        Solution solution = new Solution();
        solution.setFournisseur(fournisseur);
        solution.setTypecout(Typecout.valueOf(typecout));
        solution.setTypesolution(Typesolution.valueOf(typesolution));
        solution.setFile(fileEntity);

        return solutionRepository.save(solution);
    }



    public void deleteSolution(String id) {
        solutionRepository.deleteById(id);
    }
//    @Override
//    public void updateSolution(Solution c) {
//        Solution savedSolution = solutionRepository.findById(c.getId())
//                .orElseThrow(() -> new RuntimeException(String.format("cannot find solution by ID %d", c.getId())));
//        savedSolution.setFour(c.get());
//        savedSolution.set(c.get());
//        savedSolution.setType(c.get());
//        savedSolution.set(c.get());
//
//        solutionRepository.save(savedSolution);
//    }

    public Solution updateSolutionById(String id, Solution solution) {
        return solutionRepository.findById(id)
                .map(existingSolution -> {
                    existingSolution.setFournisseur(solution.getFournisseur());
                    existingSolution.setTypecout(solution.getTypecout());
                    existingSolution.setTypesolution(solution.getTypesolution());
                    existingSolution.setFile(solution.getFile());
                    existingSolution.setDataobject(solution.getDataobject());
                    return solutionRepository.save(existingSolution);
                })
                .orElseThrow(() -> new RuntimeException("Solution not found with id " + id));
    }



}


