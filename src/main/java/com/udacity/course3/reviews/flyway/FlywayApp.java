package com.udacity.course3.reviews.flyway;
import com.udacity.course3.reviews.entity.mysql.Comment;
import com.udacity.course3.reviews.entity.mysql.Product;
import com.udacity.course3.reviews.entity.mysql.Review;
import org.flywaydb.core.Flyway;

public class FlywayApp {
    /**
     * Basic FlywayApp used to initialize MySQL tables
     * Don't delete any of migration files
     * Don't run this application if you haven't provide new migrations
     * @see db.migration for further info about tables
     * @see Comment - JPA Entity for Comments table
     * @see Review - JPA Entity for Reviews table
     * @see Product - JPA Entity for Products table
     */
    public static void main(String[] args) {

        final String url = "jdbc:mysql://localhost:3306/reviews_project";
        final String username = "root";
        final String password = "n090f3k9m4";

        Flyway flyway = Flyway.configure().dataSource(url, username, password).load();

        flyway.migrate();
    }
}
