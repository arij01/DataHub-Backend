package com.example.demo.Controllers;

import com.example.demo.Services.FeedbacksService;
import com.example.demo.entity.Feedbacks;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/feedbacks")
public class FeedbacksController {

    private final FeedbacksService feedbacksService;

    @GetMapping("/all")
    public List<Feedbacks> getAllFeedbacks() {
        return feedbacksService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public Feedbacks getFeedbacksById(@PathVariable("id") Long id) {
        return feedbacksService.getFeedbacksById(id);
    }

    @PostMapping("/add")
    public Feedbacks addFeedbacks(@RequestBody Feedbacks feedbacks) {
        return feedbacksService.createFeedbacks(feedbacks);
    }

    @PutMapping("/update/{id}")
    public Feedbacks updateFeedbacks(@PathVariable("id") Long id, @RequestBody Feedbacks updatedFeedbacks) {
        return feedbacksService.updateFeedbacks(id, updatedFeedbacks);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFeedbacks(@PathVariable("id") Long id) {
        feedbacksService.deleteFeedbacks(id);
    }
}
