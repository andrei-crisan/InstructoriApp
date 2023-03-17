package com.fortech.instructoriautoapp.controller;

import com.fortech.instructoriautoapp.dto.ReviewDto;
import com.fortech.instructoriautoapp.model.Review;
import com.fortech.instructoriautoapp.service.ServiceImpl;
import com.fortech.instructoriautoapp.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

//    @Autowired
//    private EvaluareService evaluareService; // TODO: interfete pentru low coupling

    @Autowired
    private ServiceImpl<Review> evaluareServiceImpl;

    @Autowired
    private DtoConverter dtoConverter;


    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviews() {
        evaluareServiceImpl.setEntityBluePrint(Review.class);

        return new ResponseEntity<>(evaluareServiceImpl.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        evaluareServiceImpl.setEntityBluePrint(Review.class);
        return new ResponseEntity<>(evaluareServiceImpl.read(id), HttpStatus.OK);
    }

    @GetMapping("/dto/{id}")
    //Todo: Test method!!!
    public ResponseEntity<ReviewDto> getEvaluareByIdDto(@PathVariable Long id) {
        Review review = evaluareServiceImpl.read(id);
        return new ResponseEntity<>(dtoConverter.evaluareToDto(review), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReview(@PathVariable Long id) {
        evaluareServiceImpl.setEntityBluePrint(Review.class);
        evaluareServiceImpl.delete(id);
    }

    @PostMapping()
    public Review addReview(@RequestBody Review review) {
        evaluareServiceImpl.setEntityBluePrint(Review.class);
        evaluareServiceImpl.create(review);
        return review;
    }

}
