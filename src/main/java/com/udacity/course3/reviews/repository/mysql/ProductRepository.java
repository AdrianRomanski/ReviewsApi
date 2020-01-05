package com.udacity.course3.reviews.repository.mysql;


import com.udacity.course3.reviews.entity.mysql.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Basic JpaRepository for holding JPA Comment Entities
 * @see Product
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
