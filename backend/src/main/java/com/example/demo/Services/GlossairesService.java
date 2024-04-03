package com.example.demo.Services;

import com.example.demo.Repository.ActionLogRepository;
import com.example.demo.Repository.DatasetsRepo;
import com.example.demo.Repository.GlossairesRepo;
import com.example.demo.entity.ActionLog;
import com.example.demo.entity.Datasets;
import com.example.demo.entity.Glossaires;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class GlossairesService implements IGlossairesService {

    private final GlossairesRepo glossairesRepo;
    private final ActionLogRepository actionLogRepository;

    private  final DatasetsRepo datasetsRepo;
    public List<Glossaires> getAllGlossaires() {
        logAction("GET_ALL", "Glossaires", null); // Log action for getting all glossaires
        return glossairesRepo.findAll();
    }

    public Glossaires getGlossairesById(Long id) {
        Glossaires glossaires = glossairesRepo.findById(id).orElse(null);
        logAction("GET_BY_ID", "Glossaires", id); // Log action for getting glossaires by ID
        return glossaires;
    }

    public Glossaires createGlossaires(Glossaires glossaires, Long datasetId) {
        // Fetch the dataset by ID
        Datasets dataset = datasetsRepo.findById(datasetId).orElse(null);
        if (dataset == null) {
            // Dataset not found, you might handle this case according to your business logic
            throw new IllegalArgumentException("Dataset with id " + datasetId + " not found");
        }

        // Set the fetched dataset for the glossaire
        glossaires.setDataset(dataset);
        // Save the glossaire
        Glossaires createdGlossaires = glossairesRepo.save(glossaires);
        // Log creation action
        logAction("CREATE", "Glossaires", createdGlossaires.getId());
        return createdGlossaires;
    }



    public void deleteGlossaires(Long id) {
        glossairesRepo.deleteById(id);
        logAction("DELETE", "Glossaires", id); // Log deletion action
    }

    public Glossaires updateGlossaires(Long id, Glossaires updatedGlossaires) {
        if (glossairesRepo.existsById(id)) {
            updatedGlossaires.setId(id);
            Glossaires updated = glossairesRepo.save(updatedGlossaires);
            logAction("UPDATE", "Glossaires", id); // Log update action
            return updated;
        } else {
            return null; // or throw an exception indicating glossaires not found
        }
    }

    // Method to log actions
    private void logAction(String action, String entityName, Long entityId) {
        int userid = 1;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = getIpAddress(request);
        String httpMethod = getHttpMethod();
        String requestUri = getRequestUri();

        ActionLog log = ActionLog.builder()
                .action(action)
                .entityName(entityName)
                .entityId(entityId)
                .userid(userid)
                .ipAddress(ipAddress)
                .httpMethod(httpMethod)
                .requestUri(requestUri)
                .timestamp(LocalDateTime.now())
                .build();
        actionLogRepository.save(log);
    }

//    private String getUsername() {
//        String username = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof User) {
//            username = ((User) principal).getUsername();
//        } else if (principal instanceof String) {
//            username = (String) principal;
//        }
//        return username;
//    }

    private String getIpAddress(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader != null && !xForwardedForHeader.isEmpty()) {
            return xForwardedForHeader.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }


    private String getHttpMethod() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getMethod();
    }

    private String getRequestUri() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getRequestURI();
    }

    //logs section

}
