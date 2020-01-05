package com.udacity.course3.reviews.exceptions.notfound;

public class NotFoundException extends RuntimeException {

    /**
     * Printing message with Entity class name and id if it was not found
     */
    public NotFoundException(String className, int ID) {
        super(className + " with id: " + ID + " not found");
    }
}
