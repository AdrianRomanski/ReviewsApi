package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.TestPropertySource;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = "spring.datasource.url=")
public class ReviewsApplicationTests {


	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private CommentRepository commentRepository;

	public ReviewsApplicationTests() {
	}

	public Product createProduct() {
		Product product = new Product();
		product.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
		product.setProductName(ProductRazerMouse.PRODUCT_NAME.getString());
		product.setCategory(ProductRazerMouse.CATEGORY.getString());
		product.setManufacturer(ProductRazerMouse.MANUFACTURER.getString());
		product.setGuarantee(2);
		product.setProductDescription(ProductRazerMouse.PRODUCT_DESCRIPTION.getString());
		product.setPrice(192.97);
		productRepository.save(product);
		return product;
	}

	public Review createPositiveReview(Product product){
		Review review = new Review();
		review.setProduct(product);
		review.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
		review.setReviewerName(Reviews.POSITIVE_REVIEWER_NAME.getString());
		review.setReviewRating(10);
		review.setReviewDescription(Reviews.POSITIVE_REVIEW_DESCRIPTION.getString());
		product.addReview(review);
		reviewRepository.save(review);
		productRepository.save(product);
		return review;
	}

	public Review createNegativeReview(Product product){
		Review review = new Review();
		review.setProduct(product);
		review.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
		review.setReviewerName(Reviews.NEGATIVE_REVIEWER_NAME.getString());
		review.setReviewRating(2);
		review.setReviewDescription(Reviews.NEGATIVE_REVIEW_DESCRIPTION.getString());
		product.addReview(review);
		reviewRepository.save(review);
		productRepository.save(product);
		return review;
	}

	public Comment createCommentForPositiveReview(Product product, Review review) {
		Comment comment = new Comment();
		review.setProduct(product);
		comment.setReview(review);
		review.addComment(comment);
		product.addReview(review);
		comment.setName(Comments.POSITIVE_COMMENT_NAME.getString());
		comment.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
		comment.setCommentDescription(Comments.POSITIVE_COMMENT_DESCRIPTION.getString());
		productRepository.save(product);
		commentRepository.save(comment);
		reviewRepository.save(review);
		return comment;
	}

	public Comment createCommentForNegativeReview(Product product, Review review) {
		Comment comment = new Comment();
		comment.setReview(review);
		review.addComment(comment);
		review.setProduct(product);
		product.addReview(review);
		comment.setName(Comments.NEGATIVE_COMMENT_NAME.getString());
		comment.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
		comment.setCommentDescription(Comments.NEGATIVE_COMMENT_DESCRIPTION.getString());
		productRepository.save(product);
		commentRepository.save(comment);
		reviewRepository.save(review);
		return comment;
	}

	@Test
	public void contextLoads() {
		assertThat(productRepository).isNotNull();
		assertThat(reviewRepository).isNotNull();
		assertThat(commentRepository).isNotNull();
	}

	@Test
	public void addProduct() {
		Product product = createProduct();

		assertThat(product.getProductName()).isEqualTo(ProductRazerMouse.PRODUCT_NAME.getString());
		assertThat(product.getCategory()).isEqualTo(ProductRazerMouse.CATEGORY.getString());
		assertThat(product.getManufacturer()).isEqualTo(ProductRazerMouse.MANUFACTURER.getString());
		assertThat(product.getGuarantee()).isEqualTo(2);
		assertThat(product.getProductDescription()).isEqualTo(ProductRazerMouse.PRODUCT_DESCRIPTION.getString());
		assertThat(product.getPrice()).isEqualTo(192.97);
	}

	@Test
	public void addReviews() {
		Product product = createProduct();

		Review positiveReview = createPositiveReview(product);
		assertThat(positiveReview.getReviewerName()).isEqualTo(Reviews.POSITIVE_REVIEWER_NAME.getString());
		assertThat(positiveReview.getReviewRating()).isEqualTo(10);
		assertThat(positiveReview.getReviewDescription()).isEqualTo(Reviews.POSITIVE_REVIEW_DESCRIPTION.getString());
		assertThat(positiveReview.getProduct()).isEqualTo(product);

		Review negativeReview = createNegativeReview(product);
		assertThat(negativeReview.getReviewerName()).isEqualTo(Reviews.NEGATIVE_REVIEWER_NAME.getString());
		assertThat(negativeReview.getReviewRating()).isEqualTo(2);
		assertThat(negativeReview.getReviewDescription()).isEqualTo(Reviews.NEGATIVE_REVIEW_DESCRIPTION.getString());
		assertThat(negativeReview.getProduct()).isEqualTo(product);
		assertThat(product.getReviews().size()).isEqualTo(2);
	}

	@Test
	public void addComments() {
		Product product = createProduct();
		Review review = createPositiveReview(product);
		Comment positiveComment = createCommentForPositiveReview(product, review);
		Review negativeReview = createNegativeReview(product);
		Comment negativeReviewComment = createCommentForNegativeReview(product,negativeReview);

		assertThat(commentRepository.getOne(1).getCommentDescription()).isEqualTo(Comments.POSITIVE_COMMENT_DESCRIPTION.getString());
		assertThat(commentRepository.getOne(1).getName()).isEqualTo(Comments.POSITIVE_COMMENT_NAME.getString());
		assertThat(commentRepository.getOne(2).getCommentDescription()).isEqualTo(Comments.NEGATIVE_COMMENT_DESCRIPTION.getString());
		assertThat(commentRepository.getOne(2).getName()).isEqualTo(Comments.NEGATIVE_COMMENT_NAME.getString());
		assertThat(commentRepository.count()).isEqualTo(2);
	}

	@Test
	public void getReviewsForProduct() {
		Product product = createProduct();
		Review positiveReview = createPositiveReview(product);
		Review negativeReview = createNegativeReview(product);
		List<Review> reviews = productRepository.getOne(product.getProductID()).getReviews();

		assertThat(reviews.size()).isEqualTo(2);
		assertThat(reviews.get(0).getReviewerName()).isEqualTo(Reviews.POSITIVE_REVIEWER_NAME.getString());
		assertThat(reviews.get(0).getReviewDescription()).isEqualTo(Reviews.POSITIVE_REVIEW_DESCRIPTION.getString());
		assertThat(reviews.get(1).getReviewerName()).isEqualTo(Reviews.NEGATIVE_REVIEWER_NAME.getString());
		assertThat(reviews.get(1).getReviewDescription()).isEqualTo(Reviews.NEGATIVE_REVIEW_DESCRIPTION.getString());
	}

	@Test
	public void getCommentsForReview() {
		Product product = createProduct();
		Review positiveReview = createPositiveReview(product);
		Comment positiveReviewComment = createCommentForPositiveReview(product, positiveReview);
		List<Comment> comments = reviewRepository.getOne(positiveReview.getReviewID()).getComments();

		assertThat(comments.size()).isEqualTo(1);
		assertThat(comments.get(0).getCommentDescription()).isEqualTo(Comments.POSITIVE_COMMENT_DESCRIPTION.getString());
		assertThat(comments.get(0).getName()).isEqualTo(Comments.POSITIVE_COMMENT_NAME.getString());
	}

	@Test
	public void deleteProduct() {
		Product product = createProduct();
		assertThat(productRepository.findById(product.getProductID())).isPresent();
		productRepository.delete(product);
		assertThat(productRepository.findById(product.getProductID())).isEmpty();
	}

	@Test
	public void findProduct() {
		Product product = createProduct();
		Optional<Product> repositoryProduct = productRepository.findById(product.getProductID());

		assertThat(repositoryProduct.isPresent()).isTrue();
		assertThat(product.getProductID()).isEqualTo(repositoryProduct.get().getProductID());
		assertThat(product.getProductName()).isEqualTo(repositoryProduct.get().getProductName());
		assertThat(product.getCategory()).isEqualTo(repositoryProduct.get().getCategory());
		assertThat(product.getManufacturer()).isEqualTo(repositoryProduct.get().getManufacturer());
		assertThat(product.getGuarantee()).isEqualTo(repositoryProduct.get().getGuarantee());
		assertThat(product.getProductDescription()).isEqualTo(repositoryProduct.get().getProductDescription());
		assertThat(product.getPrice()).isEqualTo(repositoryProduct.get().getPrice());
	}
}