package com.fortech.instructoriautoapp.util;

import com.fortech.instructoriautoapp.dto.InstructorDto;
import com.fortech.instructoriautoapp.dto.ReviewDto;
import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter extends BaseConverter<Review, ReviewDto> {

    @Override
    public Review convertDtoToModel(ReviewDto dto) {
        DrivingSchool drivingSchool = DrivingSchool.builder()
                .drivingSchoolName(dto.getInstructor().getDrivingSchoolName())
                .drivingSchoolAddress(dto.getInstructor().getDrivingSchoolAddress())
                .build();

        Instructor instructor = Instructor.builder()
                .instructorName(dto.getInstructor().getInstructorName())
                .instructorSurname(dto.getInstructor().getInstructorSurname())
                .drivingSchool(drivingSchool)
                .build();
        instructor.setId(dto.getInstructor().getId());

        Review review = Review.builder()
                .instructorReview(dto.getInstructorReview())
                .experienceRating(dto.getExperienceRating())
                .instructor(instructor)
                .build();
        review.setId(dto.getId());

        return review;
    }

    @Override
    public ReviewDto convertModelToDto(Review review) {
        InstructorDto instructor = InstructorDto.builder()
                .instructorName(review.getInstructor().getInstructorName())
                .instructorSurname(review.getInstructor().getInstructorSurname())
                .drivingSchoolName(review.getInstructor().getDrivingSchool().getDrivingSchoolName())
                .drivingSchoolAddress(review.getInstructor().getDrivingSchool().getDrivingSchoolAddress())
                .reviews(review.getInstructor().getReviews())
                .build();
        instructor.setId(review.getInstructor().getId());

        ReviewDto reviewDto = ReviewDto.builder()
                .instructorReview(review.getInstructorReview())
                .experienceRating(review.getExperienceRating())
                .instructor(instructor)
                .build();
        reviewDto.setId(review.getId());

        return reviewDto;
    }
}
