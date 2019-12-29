package com.udacity.course3.reviews.flyway;
import org.flywaydb.core.Flyway;

public class FlywayApp {
    public static void main(String[] args) {

        final String url = "jdbc:mysql://localhost:3306/reviews_project";
        final String username = "root";
        final String password = "n090f3k9m4";

        Flyway flyway = Flyway.configure().dataSource(url, username, password).load();

        flyway.migrate();
    }
}
