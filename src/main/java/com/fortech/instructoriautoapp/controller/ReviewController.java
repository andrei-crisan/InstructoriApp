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
    //TODO: remove Field injection from controllers
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
        try {
            Review review = reviewServiceImpl.read(id);

            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/dto/{id}")
//    //Todo: Test method!!!
//    public ResponseEntity<ReviewDto> getEvaluareByIdDto(@PathVariable Long id) {
//        Review review = reviewServiceImpl.read(id);
//        return new ResponseEntity<>(dtoConverter.evaluareToDto(review), HttpStatus.OK);
//    }

    @PostMapping()
    @CrossOrigin(origins = "http://localhost:4200/")
    public ResponseEntity<?> saveReview(@RequestBody Review review) {
        try {
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
