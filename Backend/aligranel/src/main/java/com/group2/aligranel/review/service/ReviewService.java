package com.group2.aligranel.review.service;

import com.group2.aligranel.review.dto.ReviewResponseDTO;
import com.group2.aligranel.review.dto.ReviewRequestDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO getReviewById(ObjectId id);
    List<ReviewResponseDTO> getAllReviews();
    List<ReviewResponseDTO> getReviewsByProductId(ObjectId productId);
    ReviewResponseDTO createReview(ReviewRequestDTO review, ObjectId productId);
    void updateReview(ReviewRequestDTO review, ObjectId id);
    void deleteReview(ObjectId id);
}
