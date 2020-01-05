package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.mongodb.CommentMongo;
import com.udacity.course3.reviews.entity.mongodb.ProductMongo;
import com.udacity.course3.reviews.entity.mongodb.ReviewMongo;
import com.udacity.course3.reviews.entity.mysql.Comment;
import com.udacity.course3.reviews.entity.mysql.Review;
import com.udacity.course3.reviews.exceptions.comments.NoCommentsFoundException;
import com.udacity.course3.reviews.exceptions.notfound.NotFoundException;
import com.udacity.course3.reviews.repository.mongodb.CommentMongoRepository;
import com.udacity.course3.reviews.repository.mongodb.ProductMongoRepository;
import com.udacity.course3.reviews.repository.mongodb.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.mysql.CommentRepository;
import com.udacity.course3.reviews.repository.mysql.ReviewRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController extends Converter {

    private ReviewRepository reviewRepository;
    private CommentRepository commentRepository;

    private ReviewMongoRepository reviewMongoRepository;
    private CommentMongoRepository commentMongoRepository;
    private ProductMongoRepository productMongoRepository;

    public CommentsController(ReviewRepository reviewRepository, CommentRepository commentRepository,
                              ReviewMongoRepository reviewMongoRepository, CommentMongoRepository commentMongoRepository,
                              ProductMongoRepository productMongoRepository) {
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
        this.reviewMongoRepository = reviewMongoRepository;
        this.commentMongoRepository = commentMongoRepository;
        this.productMongoRepository = productMongoRepository;
    }

    /**
     * Creating Comment for Review with id provided by the user and save it to both MySql and MongoDB
     * @param reviewId the ID number of the Review to add Comment
     * @param comment the new Comment to be added to Databases and to Review
     * @throws NotFoundException if the Review was not found
     * @return Comment if it's successfully created and added to databases & Review
     */
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

        Optional<ReviewMongo> reviewOptional = reviewMongoRepository.findByReviewerName(review.getReviewerName());
        Optional<ProductMongo> productOptional = productMongoRepository.findByName(review.getProduct().getProductName());

        if (reviewOptional.isPresent() && productOptional.isPresent()) {
            ReviewMongo reviewMongo = reviewOptional.get();
            ProductMongo productMongo = productOptional.get();
            CommentMongo commentMongo = commentConverter(comment, reviewMongo);
            reviewMongo.addComment(commentMongo);
            productMongo.getReviews().get(reviewId - 1).addComment(commentMongo);
            reviewMongoRepository.save(reviewMongo);
            commentMongoRepository.save(commentMongo);
            productMongoRepository.save(productMongo);
        }
        return comment;
    }

    /**
     * List all Comments for Review with id provided by the user
     * @param reviewId the ID number of the Review
     * @throws NotFoundException if the Review with ID number provided was not found
     * @throws NoCommentsFoundException if there is not comments for Review
     * @return List of comments
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<Comment> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review", reviewId));
        if (review.getComments().isEmpty()) {
            throw new NoCommentsFoundException(reviewId);
        } else {
            return review.getComments();
        }
    }
}