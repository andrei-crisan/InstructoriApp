package com.fortech.instructoriautoapp.service;

import com.fortech.instructoriautoapp.exceptions.ExceptionMessages;
import com.fortech.instructoriautoapp.exceptions.ServiceException;
import com.fortech.instructoriautoapp.model.DrivingSchool;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.model.Review;
import com.fortech.instructoriautoapp.repository.DrivingSchoolRepository;
import com.fortech.instructoriautoapp.repository.InstructorRepository;
import com.fortech.instructoriautoapp.repository.ReviewRepository;
import com.fortech.instructoriautoapp.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements iService<Review> {
    private final ReviewRepository reviewRepository;
    private final InstructorRepository instructorRepository;
    private final DrivingSchoolRepository drivingSchoolRepository;
    private final Validator<Review> reviewValidator;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, InstructorRepository instructorRepository, DrivingSchoolRepository drivingSchoolRepository, Validator<Review> reviewValidator) {
        this.reviewRepository = reviewRepository;
        this.instructorRepository = instructorRepository;
        this.drivingSchoolRepository = drivingSchoolRepository;
        this.reviewValidator = reviewValidator;
    }

    @Override
    @Transactional
    public void create(Review entity) {
        Optional<Instructor> instructorToBeFound = instructorRepository.findByInstructorNameAndInstructorSurnameAndDrivingSchool_DrivingSchoolNameAndDrivingSchool_DrivingSchoolAddress(
                entity.getInstructor().getInstructorName(),
                entity.getInstructor().getInstructorSurname(),
                entity.getInstructor().getDrivingSchool().getDrivingSchoolName(),
                entity.getInstructor().getDrivingSchool().getDrivingSchoolAddress());

        Optional<DrivingSchool> drivingSchoolToBeFoundOrNot = drivingSchoolRepository.findDrivingSchoolByDrivingSchoolNameAndDrivingSchoolAddress(
                entity.getInstructor().getDrivingSchool().getDrivingSchoolName(),
                entity.getInstructor().getDrivingSchool().getDrivingSchoolAddress());

        drivingSchoolToBeFoundOrNot.ifPresent(drivingSchool -> entity.getInstructor().setDrivingSchool(drivingSchool));

        if (instructorToBeFound.isPresent()) {
            entity.setInstructor(instructorToBeFound.get());
            instructorToBeFound.get().getReviews().add(entity);
        } else {
            reviewValidator.validate(entity);
            reviewRepository.save(entity);
        }
    }

    @Override
    public List<Review> readAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review read(Long entityId) {
        return reviewRepository.findById(entityId).orElseThrow(()
                -> new ServiceException(ExceptionMessages.REVIEW_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));
    }

    @Override
    @Transactional
    public Review update(Review entity) {
        Optional<Review> reviewToBeFound = reviewRepository.findById(entity.getId());

        Review updatedReview = reviewToBeFound.orElseThrow(() ->
                new ServiceException(ExceptionMessages.REVIEW_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        updatedReview.setInstructorReview(entity.getInstructorReview());
        updatedReview.setExperienceRating(entity.getExperienceRating());

        return updatedReview;
    }

    @Override
    @Transactional
    public void delete(Long entityId) {
        Optional<Review> reviewToBeFound = reviewRepository.findById(entityId);

        Review reviewFoundOrNot = reviewToBeFound.orElseThrow(() ->
                new ServiceException(ExceptionMessages.REVIEW_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        reviewRepository.delete(reviewFoundOrNot);
    }
}
