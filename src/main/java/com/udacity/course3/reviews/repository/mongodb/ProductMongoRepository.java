package com.udacity.course3.reviews.repository.mongodb;

import com.udacity.course3.reviews.entity.mongodb.ProductMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Basic MongoRepository for storing ProductMongo Documents
 * @see ProductMongo
 */
@Repository
public interface ProductMongoRepository extends MongoRepository<ProductMongo, String> {

    void deleteById(String id);
    Optional<ProductMongo> findByName(String name);


}
