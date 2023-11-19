package com.group2.aligranel.user.repository;

import com.group2.aligranel.user.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    List<User> findAll();
    Optional<User> findById(ObjectId id);
    Optional<User> findByUsername(String username);
    boolean existsById(ObjectId id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
