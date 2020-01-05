package com.udacity.course3.reviews.repository.mysql;

import com.udacity.course3.reviews.entity.mysql.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Basic JpaRepository for holding JPA Comment Entities
 * @see Comment
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
