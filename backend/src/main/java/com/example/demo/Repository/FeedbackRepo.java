package com.example.demo.Repository;

import com.example.demo.entity.Datasets;
import com.example.demo.entity.Feedbacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedbacks,Long> {
}
