package com.example.demo.Services;

import com.example.demo.Repository.ActionLogRepository;
import com.example.demo.Repository.FeedbackRepo;
import com.example.demo.entity.ActionLog;
import com.example.demo.entity.Feedbacks;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbacksService implements IFeedbacksService {

    private final FeedbackRepo feedbackRepo;
    private final ActionLogRepository actionLogRepository;

    public List<Feedbacks> getAllFeedbacks() {
        logAction("GET_ALL", "Feedbacks", null); // Log action for getting all feedbacks
        return feedbackRepo.findAll();
    }

    public Feedbacks getFeedbacksById(Long id) {
        Feedbacks feedbacks = feedbackRepo.findById(id).orElse(null);
        logAction("GET_BY_ID", "Feedbacks", id); // Log action for getting feedbacks by ID
        return feedbacks;
    }

    public Feedbacks createFeedbacks(Feedbacks feedbacks) {
        Feedbacks createdFeedbacks = feedbackRepo.save(feedbacks);
        logAction("CREATE", "Feedbacks", createdFeedbacks.getId()); // Log creation action
        return createdFeedbacks;
    }

    public void deleteFeedbacks(Long id) {
        feedbackRepo.deleteById(id);
        logAction("DELETE", "Feedbacks", id); // Log deletion action
    }

    public Feedbacks updateFeedbacks(Long id, Feedbacks updatedFeedbacks) {
        if (feedbackRepo.existsById(id)) {
            updatedFeedbacks.setId(id);
            Feedbacks updated = feedbackRepo.save(updatedFeedbacks);
            logAction("UPDATE", "Feedbacks", id); // Log update action
            return updated;
        } else {
            return null; // or throw an exception indicating feedbacks not found
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

    public String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("RemoteAddr");
        }
        return ipAddress;
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
