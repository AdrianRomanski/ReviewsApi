package com.udacity.course3.reviews.entity.mysql;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA Entity related to Reviews Table in MySql Database
 * Auto generating unique ID values for primary keys
 * Connected to Products table with ManyToOne relation(back link('children'))
 * Connected to Comments table with OneToMany relation(forward link('parent'))
 */

@Entity
@Table(name = "Reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewID")
    private Integer reviewID;

    @Column(name = "reviewerName")
    private String reviewerName;


    @Column(name = "reviewDescription")
    private String reviewDescription;

    @Column(name = "createdTime")
    private Timestamp createdTime;

    @Min(0)
    @Max(10)
    @Column(name = "reviewRating")
    private double reviewRating;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "productID")
    private Product product;

    @OneToMany(mappedBy = "review")
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) { comments.add(comment); }

    public List<Comment> getComments() { return comments; }

    public void setComments(List<Comment> comments) { this.comments = comments; }

    public String getReviewerName() { return reviewerName; }

    public void setReviewerName(String reviewerName) { this.reviewerName = reviewerName; }

    public double getReviewRating() { return reviewRating; }

    public void setReviewRating(double reviewRating) { this.reviewRating = reviewRating; }

    public Integer getReviewID() { return reviewID; }

    public void setReviewID(Integer reviewID) { this.reviewID = reviewID; }

    public String getReviewDescription() { return reviewDescription; }

    public void setReviewDescription(String reviewDescription) { this.reviewDescription = reviewDescription; }

    public Timestamp getCreatedTime() { return createdTime; }

    public void setCreatedTime(Timestamp createdTime) { this.createdTime = createdTime; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }
}
