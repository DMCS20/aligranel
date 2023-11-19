package com.group2.aligranel.comment.repository;

import com.group2.aligranel.comment.model.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, ObjectId> {
    Optional<Comment> findById(ObjectId id);
    List<Comment> findByPostId(ObjectId postId);

}
