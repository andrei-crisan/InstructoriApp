package com.fortech.instructoriautoapp.service;

import com.fortech.instructoriautoapp.exceptions.ExceptionMessages;
import com.fortech.instructoriautoapp.exceptions.RepositoryException;
import com.fortech.instructoriautoapp.model.Instructor;
import com.fortech.instructoriautoapp.model.Review;
import com.fortech.instructoriautoapp.repository.DrivingSchoolRepository;
import com.fortech.instructoriautoapp.repository.Repository;
import com.fortech.instructoriautoapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements iService<Review> {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private Repository<Instructor, Long> instructorRepository;

    @Override
    @Transactional
    public void create(Review entity) {
        //Todo: Adaugam un review la acelasi instructor daca el exista in DB!
        Optional<Instructor> instructor = instructorRepository.findById(2L);
        if(reviewRepository.existsByInstructorReviewAndInstructor_InstructorName(entity.getInstructorReview(),
                entity.getInstructor().getInstructorName())){
            System.out.println("baaaaaa");
        }
        if (instructor.isPresent()) {
            if (entity.getInstructor().equals(instructor.get())) {
                System.out.println("Inseamna ca ei DB, deci il preluam din DB");
                entity.setInstructor(instructor.get());
            }
        }

        reviewRepository.save(entity);
    }

    @Override
    public List<Review> readAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review read(Long entityId) {
        Optional<Review> evaluareToBeFound = reviewRepository.findById(entityId);

        Review reviewFoundOrNot = evaluareToBeFound.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));
        return reviewFoundOrNot;
    }

    @Override
    @Transactional
    public Review update(Review entity) {
        Optional<Review> reviewToBeFound = reviewRepository.findById(entity.getId());

        Review updatedReview = reviewToBeFound.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        updatedReview.setInstructor(entity.getInstructor());
        updatedReview.setInstructorReview(entity.getInstructorReview());

        return updatedReview;
    }

    @Override
    @Transactional
    public void delete(Long entityId) {
        Optional<Review> evaluareToBeFound = reviewRepository.findById(entityId);

        Review reviewFoundOrNot = evaluareToBeFound.orElseThrow(() ->
                new RepositoryException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.errorMessage));

        reviewRepository.delete(reviewFoundOrNot);
    }
}
