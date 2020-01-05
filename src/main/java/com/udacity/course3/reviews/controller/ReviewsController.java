package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.mongodb.ProductMongo;
import com.udacity.course3.reviews.entity.mongodb.ReviewMongo;
import com.udacity.course3.reviews.entity.mysql.Product;
import com.udacity.course3.reviews.entity.mysql.Review;
import com.udacity.course3.reviews.exceptions.reviews.NoReviewsFoundException;
import com.udacity.course3.reviews.exceptions.notfound.NotFoundException;
import com.udacity.course3.reviews.repository.mongodb.ProductMongoRepository;
import com.udacity.course3.reviews.repository.mongodb.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.mysql.ProductRepository;
import com.udacity.course3.reviews.repository.mysql.ReviewRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController extends Converter {

    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;

    private ProductMongoRepository productMongoRepository;
    private ReviewMongoRepository reviewMongoRepository;

    public ReviewsController(ReviewRepository reviewRepository, ProductRepository productRepository,
                             ProductMongoRepository productMongoRepository, ReviewMongoRepository reviewMongoRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.productMongoRepository = productMongoRepository;
        this.reviewMongoRepository = reviewMongoRepository;
    }

    /**
     * @param id the ID number of the Product
     * @return Product
     * @throws NotFoundException if the Product with provided id is not founded
     */
    private Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product", id));
    }

    /**
     * Creating Review por Product with id entered by user and save it to both - MySql and MongoDB
     * @param productId the ID number of the Product to add Review
     * @param review the new Review object to add to databases and to Product
     * @throws NotFoundException if the Product with provided id is not founded
     * @return Review if it's successfully created and added to databases & Product
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public Review createReviewForProduct(@RequestBody @Valid Review review,
                                         @PathVariable("productId") Integer productId)  {
        Product product = findById(productId);
        review.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        review.setProduct(product);
        product.addReview(review);
        product.setRating(countRating(product));
        reviewRepository.save(review);
        productRepository.save(product);

        Optional<ProductMongo> optionalProductMongo = productMongoRepository.findById(productIDCreator(product));
        if(optionalProductMongo.isPresent()) {
            ProductMongo productMongo = optionalProductMongo.get();
            productMongo.addReview(reviewConverter(review, productMongo));
            productMongo.setRating(countRating(product));
            reviewMongoRepository.save(reviewConverter(review, productMongo));
            productMongoRepository.save(productMongo);
        }
        return review;
    }

    /**
     * @param productId the ID number of the Product
     * @throws NotFoundException if the Product is not found
     * @throws NoReviewsFoundException if there is not Reviews found for Product
     * @return a list of Reviews from MongoDB
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public List<ReviewMongo> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        ProductMongo productMongo = productMongoRepository.findById(productIDCreator(findById(productId))).orElseThrow(()
                -> new NotFoundException("Product", productId));
        if (productMongo.getReviews().isEmpty()) {
            throw new NoReviewsFoundException(productId);
        }
        return productMongo.getReviews();
    }

    /**
     * Counting rating for Product
    */
    private double countRating(Product product) {
        double reviewsCount = 0.0;
        double rating;
        double totalReviews = 0.0;
        List<Review> reviewList = product.getReviews();
        for (int i = 0; i < product.getReviews().size(); i++) {
            reviewsCount += 1;
            totalReviews += reviewList.get(i).getReviewRating();
        }
        rating = totalReviews / reviewsCount;
        return rating;
    }
}