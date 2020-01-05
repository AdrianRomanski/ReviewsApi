package com.udacity.course3.reviews.mysql.enums;

/** Enum class created for better readability while testing
 * It supports creating 2 Comments - Negative and Positive
 * Values can be access through getString method
 **/
public enum Comments {
    POSITIVE_COMMENT_NAME("Anthony Razer"),
    POSITIVE_COMMENT_DESCRIPTION("We are very happy that you had great time with our mouse" + "\n"+
                                         "'for gamers by gamers' Razer "),
    NEGATIVE_COMMENT_NAME("Dominik Customer Relation Service"),
    NEGATIVE_COMMENT_DESCRIPTION("We are very sorry that you have a feel cheated probably you have choosen a wrong version of mouse but dont worry!" + "\n" +
            "We would like to give you a free exchange of your gaming mouse for left handed version furthermore" + "\n" +
            "a special gift is comming to you inside package :) 'for gamers by gamers' Razer ");

    private final String string;

    Comments(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
