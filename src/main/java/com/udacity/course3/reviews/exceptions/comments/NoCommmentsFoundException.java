package com.udacity.course3.reviews.exceptions.comments;

public class NoCommmentsFoundException extends RuntimeException {

    public NoCommmentsFoundException(Integer reviewID) {
        super("There is no comments for review with id: ");
    }
}
