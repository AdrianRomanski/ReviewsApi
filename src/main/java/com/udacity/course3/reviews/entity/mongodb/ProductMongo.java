package com.udacity.course3.reviews.entity.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * MongoDB Entity related to Products Collection
 * Nesting Reviews documents to Product Entity
 */
@Document(collection = "Products")
public class ProductMongo {

    @Id
    private String id;

    private String name;

    private String description;

    private String manufacturer;

    private String category;

    private Double price;

    private Double rating;

    private Integer guarantee;

    private LocalDateTime localDateTime;

    private List<ReviewMongo> reviews = new ArrayList<>();

    public void addReview(ReviewMongo reviewMongo) { this.reviews.add(reviewMongo); }

    public List<ReviewMongo> getReviews() { return this.reviews; }

    public void setReviews(List<ReviewMongo> reviews) { this.reviews = reviews; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getManufacturer() { return manufacturer; }

    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public Double getRating() { return rating; }

    public void setRating(Double rating) { this.rating = rating; }

    public Integer getGuarantee() { return guarantee; }

    public void setGuarantee(Integer guarantee) { this.guarantee = guarantee; }

    public LocalDateTime getLocalDateTime() { return localDateTime; }

    public void setLocalDateTime(LocalDateTime localDateTime) { this.localDateTime = localDateTime; }
}




