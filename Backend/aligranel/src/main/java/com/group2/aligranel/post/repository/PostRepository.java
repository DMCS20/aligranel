package com.group2.aligranel.post.repository;

import com.group2.aligranel.post.model.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, ObjectId> {
    List<Post> findAll();
    Optional<Post> findById(ObjectId id);
    List<Post> findByUserId(ObjectId userId);
    boolean existsById(ObjectId id);
    boolean existsByContent(String content);
}
