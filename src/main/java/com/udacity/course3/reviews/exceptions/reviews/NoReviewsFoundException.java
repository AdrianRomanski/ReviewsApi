package com.udacity.course3.reviews.exceptions.reviews;

public class NoReviewsFoundException extends RuntimeException {

    public NoReviewsFoundException(int productID) {
        super("No reviews found for product with id: " + productID);
    }
}
