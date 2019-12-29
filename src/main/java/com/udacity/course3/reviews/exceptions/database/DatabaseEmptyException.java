package com.udacity.course3.reviews.exceptions.database;

public class DatabaseEmptyException extends RuntimeException {

    public DatabaseEmptyException() {
        super("Database is empty");
    }
}
