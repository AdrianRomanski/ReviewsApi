package com.udacity.course3.reviews.exceptions.notfound;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String className, int ID) {
        super(className + " with id: " + ID + " not found");
    }
}
