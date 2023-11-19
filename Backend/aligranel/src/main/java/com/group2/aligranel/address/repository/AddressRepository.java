package com.group2.aligranel.address.repository;

import com.group2.aligranel.address.model.Address;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends MongoRepository<Address, ObjectId> {
    List<Address> findAll();
    Optional<Address> findById(ObjectId id);
    List<Address> findByUserId(ObjectId userId);
    boolean existsByName(String name);
}
