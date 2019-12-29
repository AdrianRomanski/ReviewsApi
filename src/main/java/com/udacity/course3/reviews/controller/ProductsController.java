package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.exceptions.database.DatabaseEmptyException;
import com.udacity.course3.reviews.exceptions.notfound.NotFoundException;
import com.udacity.course3.reviews.repository.ProductRepository;

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
public class ProductsController {

    private ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Valid Product product) {
        product.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        productRepository.save(product);
        return product;
    }

    @RequestMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product findById(@PathVariable("id") Integer id) throws Exception {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product", id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.GONE)
    public String deleteProduct(@PathVariable("id") Integer id) throws Exception {
        productRepository.delete(findById(id));
        return  "" + Timestamp.valueOf(LocalDateTime.now()) + "\n" +
                "Product with id: " + id + " successfully deleted";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> listProducts() throws Exception {
        if (productRepository.count() == 0) {
            throw new DatabaseEmptyException();
        } else {
            return productRepository.findAll();
        }
    }
}
