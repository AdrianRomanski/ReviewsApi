package com.udacity.course3.reviews.mongodb;

import com.udacity.course3.reviews.entity.mongodb.ProductMongo;
import com.udacity.course3.reviews.entity.mongodb.ReviewMongo;
import com.udacity.course3.reviews.repository.mongodb.ProductMongoRepository;
import com.udacity.course3.reviews.repository.mongodb.ReviewMongoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Testing MongoDB Repository methods
 * Repositories
 * @see ProductMongoRepository
 * @see ReviewMongoRepository
 */
@DataMongoTest
@ExtendWith(SpringExtension.class)
class MongoDbRepositoryMethodsTests {

    @Autowired
    private ProductMongoRepository productMongoRepository;
    @Autowired
    private ReviewMongoRepository reviewMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Cleaning collection
     * Creating and saving MongoProduct to repository
     * @return MongoProduct
     */
    ProductMongo createMongoProduct() {
        mongoTemplate.dropCollection(ProductMongo.class);
        ProductMongo productMongo = new ProductMongo();
        productMongo.setName("Chroma");
        productMongo.setManufacturer("Razer");
        productMongo.setId("Chroma_Razer");
        productMongoRepository.save(productMongo);
        return productMongo;
    }

    /**
     * Cleaning collection
     * Creating and saving MongoReview to repository
     * @return ReviewMongo
     */
    ReviewMongo createMongoReview() {
        mongoTemplate.dropCollection(ReviewMongo.class);
        ReviewMongo reviewMongo = new ReviewMongo();
        reviewMongo.setReviewerName("Adrian");
        reviewMongo.setId("Adrian_Chroma_Razer");
        reviewMongoRepository.save(reviewMongo);
        return reviewMongo;
    }

    MongoDbRepositoryMethodsTests() {
    }

    @Test
    void contextLoads() {
        assertThat(productMongoRepository).isNotNull();
        assertThat(reviewMongoRepository).isNotNull();
    }

    /**
     * Testing findByReviewerName from ReviewMongoRepository
     * @see ReviewMongoRepository#findByReviewerName(java.lang.String)
     */
    @Test
    void findReviewByReviewerName() {
        ReviewMongo reviewMongo = createMongoReview();
        Optional<ReviewMongo> optional = reviewMongoRepository.findByReviewerName(reviewMongo.getReviewerName());
        optional.ifPresent(mongo -> assertEquals(mongo.getReviewerName(), reviewMongo.getReviewerName()));
    }

    /**
     * Testing findByName from ProductMongoRepository
     * @see ProductMongoRepository#findByName(java.lang.String)
     */
    @Test
    void findProductByName() {
        ProductMongo productMongo = createMongoProduct();
        Optional<ProductMongo> optional = productMongoRepository.findByName(productMongo.getName());
        optional.ifPresent(mongo -> assertEquals(mongo.getName(), productMongo.getName()));
    }

    /**
     * Testing deleteByID from ProductMongoRepository
     * @see ProductMongoRepository#deleteById(java.lang.String)
     */
    @Test
    void deleteByID() {
        ProductMongo productMongo = createMongoProduct();
        productMongoRepository.save(productMongo);
        productMongoRepository.deleteById(productMongo.getId());
        Optional<ProductMongo> optional = productMongoRepository.findById(productMongo.getId());
        optional.ifPresent(mongo -> productMongoRepository.deleteById(mongo.getId()));
        assertThat(productMongoRepository.findById(productMongo.getId())).isEmpty();
    }
}
