package com.udacity.course3.reviews.exceptions.database;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class DatabaseGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DatabaseEmptyException.class)
    public void emptyDatabase(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
