package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.exceptions.ServiceException;
import com.fortech.instructoriautoapp.model.Review;
import com.fortech.instructoriautoapp.service.ReviewServiceImpl;
import com.fortech.instructoriautoapp.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    @Autowired
    private DtoConverter dtoConverter;


    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity<>(reviewServiceImpl.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return new ResponseEntity<>(reviewServiceImpl.read(id), HttpStatus.OK);
    }

//    @GetMapping("/dto/{id}")
//    //Todo: Test method!!!
//    public ResponseEntity<ReviewDto> getEvaluareByIdDto(@PathVariable Long id) {
//        Review review = reviewServiceImpl.read(id);
//        return new ResponseEntity<>(dtoConverter.evaluareToDto(review), HttpStatus.OK);
//    }

    @PostMapping()
    public Review saveReview(@RequestBody Review review) {
        reviewServiceImpl.create(review);
        return review;
    }

    @PutMapping
    public ResponseEntity<Review> updateReview(@RequestBody Review review) {
        try {
            Review updatedReview = reviewServiceImpl.update(review);

            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/rm/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewServiceImpl.delete(id);
    }

}
