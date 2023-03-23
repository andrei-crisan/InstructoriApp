package com.fortech.instructoriautoapp.repository;

import com.fortech.instructoriautoapp.model.Review;
import org.springframework.stereotype.Component;

@Component
public interface ReviewRepository extends Repository<Review, Long> {
    boolean existsByInstructorReviewAndInstructor_InstructorName(String review, String instructor);
}
