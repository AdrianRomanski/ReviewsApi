package com.udacity.course3.reviews.repository.mysql;

import com.udacity.course3.reviews.entity.mysql.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Basic JpaRepository for holding JPA Comment Entities
 * @see Review
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
