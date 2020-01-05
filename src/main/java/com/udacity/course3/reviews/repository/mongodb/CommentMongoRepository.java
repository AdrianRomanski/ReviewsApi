package com.udacity.course3.reviews.repository.mongodb;

import com.udacity.course3.reviews.entity.mongodb.CommentMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Basic MongoRepository for storing CommentMongo Documents
 * @see CommentMongo
 */
@Repository
public interface CommentMongoRepository extends MongoRepository<CommentMongo, String> {
}
