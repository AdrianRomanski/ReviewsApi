package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.exceptions.reviews.NoReviewsFoundException;
import com.udacity.course3.reviews.exceptions.notfound.NotFoundException;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    private ReviewRepository reviewRepository;
    private ProductRepository productRepository;


    public ReviewsController(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public Review createReviewForProduct(@RequestBody @Valid Review review,
                                                    @PathVariable("productId") Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(()
                -> new NotFoundException("Product", productId));

        review.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        review.setProduct(product);
        product.addReview(review);

        int reviewsCount = 0;
        double rating = 0.0;
        double totalReview = 0.0;

        List<Review> reviewList = product.getReviews();

        for(int i = 0; i < product.getReviews().size(); i++) {
            reviewsCount+= 1;
            totalReview += reviewList.get(i).getReviewRating();
        }
        rating = totalReview / reviewsCount;
        product.setRating(rating);
        reviewRepository.save(review);
        productRepository.save(product);
        return review;
    }

    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public List<Review> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(()
                -> new NotFoundException("Product", productId));
        if(product.getReviews().isEmpty()) {
            throw new NoReviewsFoundException(productId);
        }
        return product.getReviews();
    }
}