package com.example.demo.Services;

import com.example.demo.entity.Feedbacks;

import java.util.List;

public interface IFeedbacksService {
    List<Feedbacks> getAllFeedbacks();
    Feedbacks getFeedbacksById(Long id);
    Feedbacks createFeedbacks(Feedbacks feedbacks);
    void deleteFeedbacks(Long id);
    Feedbacks updateFeedbacks(Long id, Feedbacks updatedFeedbacks);
}
