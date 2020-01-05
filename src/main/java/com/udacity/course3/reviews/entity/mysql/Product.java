package com.udacity.course3.reviews.entity.mysql;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA Entity related to Products Table in MySql Database
 * Auto generating unique ID values for primary keys
 * Connected to Reviews table with OneToMany relation(forward link('parent'))
 */

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "productID")
    private Integer productID;


    @Column(name = "productName")
    private String productName;


    @Column(name = "productDescription")
    private String productDescription;


    @Column(name = "manufacturer")
    private String manufacturer;


    @Column(name = "category")
    private String category;

    @Min(1)
    @Column(name = "price")
    private Double price;

    @Min(0)
    @Max(100)
    @Column(name = "guarantee")
    private Integer guarantee;

    @Min(0)
    @Max(10)
    @Column(name = "rating")
    private Double rating;

    @Column(name = "createdTime")
    private Timestamp createdTime;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public List<Review> getReviews() { return reviews; }

    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public Integer getGuarantee() { return guarantee; }

    public void setGuarantee(Integer guarantee) { this.guarantee = guarantee; }

    public Double getRating() { return rating; }

    public void setRating(Double rating) { this.rating = rating; }

    public void addReview(Review review) { reviews.add(review); }
}
