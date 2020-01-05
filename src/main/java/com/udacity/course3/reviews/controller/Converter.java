package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.mongodb.CommentMongo;
import com.udacity.course3.reviews.entity.mongodb.ProductMongo;
import com.udacity.course3.reviews.entity.mongodb.ReviewMongo;
import com.udacity.course3.reviews.entity.mysql.Comment;
import com.udacity.course3.reviews.entity.mysql.Product;
import com.udacity.course3.reviews.entity.mysql.Review;

import java.time.LocalDateTime;

/**
 * Supports converting MySql JPA Entity Objects to MongoDB Documents
 * Class created to avoid duplicate code and to provide more code readability in Controller classes
 */
public abstract class Converter {


    /**
     * Converting MySql Product object to ProductMongo Document
     * @param product MySql Object
     * @return ProductMongo Document
     */
    ProductMongo productConverter(Product product) {
        ProductMongo productMongo = new ProductMongo();
        productMongo.setId(productIDCreator(product));
        productMongo.setLocalDateTime(LocalDateTime.now());
        productMongo.setName(product.getProductName());
        productMongo.setPrice(product.getPrice());
        productMongo.setManufacturer(product.getManufacturer());
        productMongo.setGuarantee(product.getGuarantee());
        productMongo.setDescription(product.getProductDescription());
        productMongo.setCategory(product.getCategory());
        productMongo.setRating(product.getRating());
        return productMongo;
    }

    /**
     * Creating ID for product based on product name and category
     * @param product MySql object needed for ID creation(get Product name and category)
     * @return Product ID String
     */
    String productIDCreator(Product product) {
        return product.getProductName() + "_" + product.getCategory();
    }


    /**
     * Converting MySql Review object to ReviewMongo Document
     * @param review MySql object that need to be converted to Document
     * @param productMongo needed to create ID for review
     * @return ReviewMongo Document
     */
    ReviewMongo reviewConverter(Review review, ProductMongo productMongo) {
        ReviewMongo reviewMongo = new ReviewMongo();
        reviewMongo.setLocalDateTime(LocalDateTime.now());
        reviewMongo.setId(reviewIDCreator(review, productMongo));
        reviewMongo.setRating(review.getReviewRating());
        reviewMongo.setDescription(review.getReviewDescription());
        reviewMongo.setReviewerName(review.getReviewerName());
        return reviewMongo;
    }

    /**
     * Creating id for Review based on Reviewer name and ID of MongoProduct
     * @param review MySql object needed for ID creation(get Reviewer name)
     * @param productMongo Mongo Document needed for ID creation(get id)
     * @return Review ID String
     */
     private String reviewIDCreator(Review review, ProductMongo productMongo) {
        return review.getReviewerName() + "_" + productMongo.getId();
    }

    /**
     * Converting MySql object to CommentMongo Document
     * @param comment MySql object that need to be converted to CommentMongo Document
     * @param reviewMongo needed for creating ID for comment
     * @return CommentMongo Document
     */
    CommentMongo commentConverter(Comment comment, ReviewMongo reviewMongo) {
        CommentMongo commentMongo = new CommentMongo();
        commentMongo.setId(commentIDCreator(comment, reviewMongo));
        commentMongo.setName(comment.getName());
        commentMongo.setDescription(comment.getCommentDescription());
        commentMongo.setLocalDateTime(LocalDateTime.now());
        return commentMongo;
    }


    /**
     * Creating ID for comment based od comment name and ID of reviewMongo
     * @param comment MySql object needed for ID creation(get name)
     * @param reviewMongo Mongo Document needed for ID creation(get id)
     * @return Comment ID  String
     */
    private String commentIDCreator(Comment comment, ReviewMongo reviewMongo) {
        return comment.getName() + "_" + reviewMongo.getId();
    }

}
