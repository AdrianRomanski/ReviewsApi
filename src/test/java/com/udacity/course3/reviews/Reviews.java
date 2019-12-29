package com.udacity.course3.reviews;

public enum Reviews {
    POSITIVE_REVIEWER_NAME("Adrian"),
    POSITIVE_REVIEW_DESCRIPTION("Perfect mouse for long raids totally a must for MMO gamers" + "\n" +
                               "to be honest that's the best mouse in my life!!!"),
    NEGATIVE_REVIEWER_NAME("Piotrek"),
    NEGATIVE_REVIEW_DESCRIPTION(("I can't say that the mouse is pretty and comprehensive but as left handed player i feel" +
                               "cheated by the company as it says that is suitable for both left and right handed gamers." + "\n" +
                               "Selecting on of the 12 active buttons with small finger rather than thumb it's just impossible!" + "\n" +
                               "Shame! Shame! Shame!"));

    private final String string;

    Reviews(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}


