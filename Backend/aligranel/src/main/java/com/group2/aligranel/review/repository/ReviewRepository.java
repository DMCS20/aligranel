package com.group2.aligranel.review.repository;

import com.group2.aligranel.review.model.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, ObjectId>{
    Optional<Review> findById(ObjectId id);
    List<Review> findAllByProductId(ObjectId productId);
    List<Review> findAll();
}
