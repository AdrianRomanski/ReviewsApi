package com.udacity.course3.reviews.exceptions.reviews;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Basic Exception handler changing HttpStatus from 500 to 404 NOT FOUND(Avoid to crush API after errors)
 * @see NoReviewsFoundException
 */
@ControllerAdvice
public class NoReviewsFoundExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoReviewsFoundException.class)
    public void NoReviewsFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }



}
