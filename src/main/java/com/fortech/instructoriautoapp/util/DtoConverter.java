package com.fortech.instructoriautoapp.util;

import com.fortech.instructoriautoapp.dto.ReviewDto;
import com.fortech.instructoriautoapp.model.Review;
import org.springframework.stereotype.Component;


@Component
public class DtoConverter {

    public ReviewDto evaluareToDto(Review review){
        return new ReviewDto(review.getInstructorReview(),
                review.getInstructor().getInstructorName(),
                review.getInstructor().getInstructorSurname(), review.getExperienceRating());
    }
    //Todo: Converter from DTO to Evaluare and other entities;

}
