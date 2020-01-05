package com.udacity.course3.reviews.mongodb_init;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.udacity.course3.reviews.entity.mongodb.CommentMongo;
import com.udacity.course3.reviews.entity.mongodb.ProductMongo;
import com.udacity.course3.reviews.entity.mongodb.ReviewMongo;

public class MongoDB {
    /**
     * Initializing MongoDB Collections
     * Don't run this class more than once if the Collections still exists otherwise it will produce an error
     * For creation of new Collection you have to change argument in database.createCollection
     * And create Collection Entity
     * @see CommentMongo - Comments Colletion Entity
     * @see ProductMongo - Products Colletction Entity
     * @see ReviewMongo - Reviews Collection Entity
     */
    public static void main(String[] args) {

        final String uri = "mongodb://course3:course3@localhost:27017/udacity";

        MongoClient client = MongoClients.create(uri);

        MongoDatabase database = client.getDatabase("udacity");

        database.createCollection("Products");
        database.createCollection("Reviews");
        database.createCollection("Comments");

        client.close();
    }
}
