package com.group2.aligranel.review.controller;

import com.group2.aligranel.review.dto.ReviewRequestDTO;
import com.group2.aligranel.review.dto.ReviewResponseDTO;
import com.group2.aligranel.review.service.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Transactional(readOnly = true)
    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewResponseDTO> getReviewById(@PathVariable ObjectId id){
        return new ResponseEntity<>(reviewService.getReviewById(id), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByProductId(@PathVariable ObjectId productId){
        return new ResponseEntity<>(reviewService.getReviewsByProductId(productId), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews(){
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<ReviewResponseDTO> createReview(@RequestBody ReviewRequestDTO reviewRequest, @PathVariable ObjectId productId){
        return new ResponseEntity<>(reviewService.createReview(reviewRequest, productId), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/reviews/{id}")
    public ResponseEntity updateReview(@RequestBody ReviewRequestDTO reviewRequest, @PathVariable ObjectId id){
        reviewService.updateReview(reviewRequest, id);
        return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity deleteReview(@PathVariable ObjectId id){
        reviewService.deleteReview(id);
        return new ResponseEntity<>("Review with ID" + id + " has been deleted successfully", HttpStatus.OK);
    }
}
