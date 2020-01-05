package com.udacity.course3.reviews.exceptions.comments;

public class NoCommentsFoundException extends RuntimeException {
    /**
     * Printing message with id of the review that's missing
     */
    public NoCommentsFoundException(Integer reviewID) {
        super("There is no comments for review with id: " + reviewID);
    }
}
