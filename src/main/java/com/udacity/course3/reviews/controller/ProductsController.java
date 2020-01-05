package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.mysql.Product;
import com.udacity.course3.reviews.exceptions.database.DatabaseEmptyException;
import com.udacity.course3.reviews.exceptions.notfound.NotFoundException;
import com.udacity.course3.reviews.repository.mongodb.ProductMongoRepository;
import com.udacity.course3.reviews.repository.mysql.ProductRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController extends Converter {

    private ProductRepository productRepository;

    private ProductMongoRepository productMongoRepository;

    public ProductsController(ProductRepository productRepository, ProductMongoRepository productMongoRepository) {
        this.productRepository = productRepository;
        this.productMongoRepository = productMongoRepository;
    }

    /**
     * @param product body with all fields required (except rating and guarantee)
     * @return Product if successfully created and saved to database
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Valid Product product) {
        product.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        productRepository.save(product);
        productMongoRepository.save(productConverter(product));
        return product;
    }

    /**
     * @param id the ID number of the Product
     * @return Product
     * @throws NotFoundException if the Product with provided id was not found
     */
    @RequestMapping(value = "/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product findById(@PathVariable("id") Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product", id));
    }

    /**
     * Deleting Product from both MySql and MongoDb databases
     * @param id of Product to be deleted
     * @throws NotFoundException if the Product with id provided was not found
     * @return message if the Product is successfully deleted
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.GONE)
    public String deleteProduct(@PathVariable("id") Integer id) {
        productMongoRepository.deleteById(productIDCreator(findById(id)));
        productRepository.delete(findById(id));
        return "" + Timestamp.valueOf(LocalDateTime.now()) + "\n" +
                "Product with id: " + id + " successfully deleted";
    }

    /**
     * @return List of Products from MySql Database
     * @throws DatabaseEmptyException if there is no Products to list
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> listProducts() {
        if (productRepository.count() == 0) {
            throw new DatabaseEmptyException();
        } else {
            return productRepository.findAll();
        }
    }
}