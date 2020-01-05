package com.udacity.course3.reviews.mongodb;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.udacity.course3.reviews.entity.mongodb.CommentMongo;
import com.udacity.course3.reviews.entity.mongodb.ProductMongo;
import com.udacity.course3.reviews.entity.mongodb.ReviewMongo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
@ExtendWith(SpringExtension.class)
class EmbeddedMongoDbTest {



    @Test
    void test(@Autowired MongoTemplate mongoTemplate) {

        mongoTemplate.dropCollection(ProductMongo.class);
        mongoTemplate.dropCollection(ReviewMongo.class);
        mongoTemplate.dropCollection(CommentMongo.class);


        DBObject Product = BasicDBObjectBuilder.start()
                .add("name", "Naga Chroma V2")
                .add("description", "The Razer Naga Chroma multi-color MMO gaming mouse makes late night raids, " +
                        "intense guild battles, and exciting dungeon crawling even more fun for MMO gamers. " +
                        "It comes with 12 mechanical thumb-grid buttons for faster in-game actuations and assured tactile feedback.")
                .get();

        DBObject Review = BasicDBObjectBuilder.start()
                .add("reviewer_name", "Piotrek")
                .add("description", "Best mouse in my life")
                .get();

        DBObject Comment = BasicDBObjectBuilder.start()
                .add("name", "Anthony Razer")
                .add("description", "We are very happy that you had great time with our mouse" + "\n"+
                        "'for gamers by gamers' Razer ")
                .get();

        mongoTemplate.save(Product, "Products");
        mongoTemplate.save(Review, "Reviews");
        mongoTemplate.save(Comment, "Comments");

        assertThat(mongoTemplate.findAll(DBObject.class, "Products")).extracting("name")
                .containsOnly("Naga Chroma V2");
        assertThat(mongoTemplate.findAll(DBObject.class, "Reviews")).extracting("reviewer_name")
                .containsOnly("Piotrek");
        assertThat(mongoTemplate.findAll(DBObject.class, "Comments")).extracting("name")
                .containsOnly("Anthony Razer");

    }
}
