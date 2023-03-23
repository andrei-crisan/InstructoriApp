package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.dto.ReviewDto;
import com.fortech.instructoriautoapp.exceptions.ServiceException;
import com.fortech.instructoriautoapp.model.Review;
import com.fortech.instructoriautoapp.service.ReviewServiceImpl;
import com.fortech.instructoriautoapp.util.DtoConverter;
import com.fortech.instructoriautoapp.util.ReviewConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:4200/")
public class ReviewController {
    //TODO: remove Field injection from controllers
    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    @Autowired
    private DtoConverter dtoConverter;
    @Autowired
    private ReviewConverter reviewConverter;

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<Review> reviews = reviewServiceImpl.readAll();
        return new ResponseEntity<>(reviewConverter.convertModelsToDtos(reviews), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
        try {
            Review review = reviewServiceImpl.read(id);
            return new ResponseEntity<>(reviewConverter.convertModelToDto(review), HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping()
    public ResponseEntity<?> saveReview(@RequestBody ReviewDto reviewDto) {
        try {
            Review review = reviewConverter.convertDtoToModel(reviewDto);
            reviewServiceImpl.create(review);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping
    public ResponseEntity<Review> updateReview(@RequestBody Review review) {
        try {
            Review updatedReview = reviewServiceImpl.update(review);

            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/rm/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        try {
            reviewServiceImpl.delete(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
