package com.group2.aligranel.review.service.impl;

import com.group2.aligranel.review.dto.ReviewRequestDTO;
import com.group2.aligranel.review.dto.ReviewResponseDTO;
import com.group2.aligranel.review.mapper.ReviewMapper;
import com.group2.aligranel.review.model.Review;
import com.group2.aligranel.review.repository.ReviewRepository;
import com.group2.aligranel.review.service.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewMapper reviewMapper;
    @Override
    public ReviewResponseDTO getReviewById(ObjectId id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);

        if(!reviewOptional.isPresent()){
            //TODO: NotFoundException
        }

        return reviewMapper.toReviewResponseDTO(reviewOptional.get());
    }

    @Override
    public List<ReviewResponseDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviewMapper.toReviewResponseDTOList(reviews);
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByProductId(ObjectId productId) {
        List<Review> reviews = reviewRepository.findAllByProductId(productId);
        return reviewMapper.toReviewResponseDTOList(reviews);
    }

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO reviewRequest, ObjectId productId) {
        Review review = reviewMapper.toReview(reviewRequest);
        review.setProductId(productId);
        review.setUserId(new ObjectId(reviewRequest.getUserId()));
        reviewRepository.insert(review);
        return reviewMapper.toReviewResponseDTO(review);
    }

    @Override
    public void updateReview(ReviewRequestDTO review, ObjectId id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if(reviewOptional.isEmpty()){
            //TODO: NotFoundException
        }

        reviewMapper.updateReviewFromDTO(review, reviewOptional.get());
        reviewOptional.get().setUserId(new ObjectId(review.getUserId()));
        reviewRepository.save(reviewOptional.get());
    }

    @Override
    public void deleteReview(ObjectId id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if(reviewOptional.isEmpty()){
            //TODO: NotFoundException
        }

        reviewRepository.delete(reviewOptional.get());
    }
}
