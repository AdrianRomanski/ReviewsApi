package com.udacity.course3.reviews.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "commentID")
    private Integer commentID;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "createdTime")
    private Timestamp createdTime;

    @JoinColumn(name = "commentDescription")
    private String commentDescription;

    @ManyToOne
    @JoinColumn(name = "reviewID")
    @JsonBackReference
    private Review review;

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Integer getCommentId() {
        return commentID;
    }

    public void setCommentId(Integer commentId) {
        this.commentID = commentId;
    }

    public Integer getCommentID() { return commentID; }

    public void setCommentID(Integer commentID) { this.commentID = commentID; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }
}