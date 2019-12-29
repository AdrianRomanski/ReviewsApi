package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.exceptions.comments.NoCommmentsFoundException;
import com.udacity.course3.reviews.exceptions.notfound.NotFoundException;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    private ReviewRepository reviewRepository;
    private CommentRepository commentRepository;

    public CommentsController(ReviewRepository reviewRepository, CommentRepository commentRepository) {
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
    }

    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public Comment createCommentForReview(@RequestBody @Valid Comment comment,
                                          @PathVariable("reviewId") Integer reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review", reviewId));
        comment.setReview(review);
        comment.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        review.addComment(comment);
        commentRepository.save(comment);
        reviewRepository.save(review);
        return comment;
    }

    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<Comment> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review", reviewId));
        if (review.getComments().isEmpty()) {
            throw new NoCommmentsFoundException(reviewId);
        } else {
            return review.getComments();
        }
    }
}