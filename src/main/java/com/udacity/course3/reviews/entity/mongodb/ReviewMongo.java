package com.udacity.course3.reviews.entity.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * MongoDB Entity related to Reviews Collection
 * Nesting Comments documents to Review Entity
 */
@Document(collection = "Reviews")
public class ReviewMongo {

    @Id
    private String id;

    private String reviewerName;

    private String description;

    private Double rating;

    private LocalDateTime localDateTime;

    private List<CommentMongo> comments = new ArrayList<>();

    public void addComment(CommentMongo commentMongo) { this.comments.add(commentMongo); }

    public List<CommentMongo> getComments() { return comments; }

    public void setComments(List<CommentMongo> comments) { this.comments = comments; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getReviewerName() { return reviewerName; }

    public void setReviewerName(String reviewerName) { this.reviewerName = reviewerName; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Double getRating() { return rating; }

    public void setRating(Double rating) { this.rating = rating; }

    public LocalDateTime getLocalDateTime() { return localDateTime; }

    public void setLocalDateTime(LocalDateTime localDateTime) { this.localDateTime = localDateTime; }
}
