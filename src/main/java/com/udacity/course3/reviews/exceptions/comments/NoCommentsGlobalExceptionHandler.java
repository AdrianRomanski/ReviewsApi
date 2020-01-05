package com.udacity.course3.reviews.exceptions.comments;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Basic Exception handler changing HttpStatus from 500 to 404 NOT FOUND(Avoid to crush API after errors)
 * @see NoCommentsFoundException
 */
@ControllerAdvice
public class NoCommentsGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoCommentsFoundException.class)
    public void noComments(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
