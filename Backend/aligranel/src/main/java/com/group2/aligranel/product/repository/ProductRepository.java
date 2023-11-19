package com.group2.aligranel.product.repository;

import com.group2.aligranel.product.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    Optional<Product> findById(ObjectId id);
    List<Product> findAll();
    List<Product> findByCategoriesContainingIgnoreCase(String category);
}
