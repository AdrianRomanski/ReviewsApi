package com.udacity.course3.reviews.repository.mongodb;

import com.udacity.course3.reviews.entity.mongodb.ReviewMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Basic MongoRepository for storing CommentMongo Documents
 * @see ReviewMongo
 */
@Repository
public interface ReviewMongoRepository extends MongoRepository<ReviewMongo, String> {

    Optional<ReviewMongo> findByReviewerName(String reviewerName);

}
