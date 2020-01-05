package com.udacity.course3.reviews.exceptions.database;

public class DatabaseEmptyException extends RuntimeException {
    /**
     * Printing message
     */
    public DatabaseEmptyException() {
        super("Database is empty");
    }
}
