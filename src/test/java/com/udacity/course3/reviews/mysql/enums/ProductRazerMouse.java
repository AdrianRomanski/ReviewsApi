package com.udacity.course3.reviews.mysql.enums;

/** Enum class created for better readability while testing
 * It supports creating 1 Product
 * Values can be access through getString method
 **/
public enum ProductRazerMouse {

    PRODUCT_NAME("Naga Chroma V2"),
    CATEGORY("Gaming mouse"),
    MANUFACTURER("Razer"),
    PRODUCT_DESCRIPTION("The Razer Naga Chroma multi-color MMO gaming mouse makes late night raids, " +
            "intense guild battles, and exciting dungeon crawling even more fun for MMO gamers. " +
            "It comes with 12 mechanical thumb-grid buttons for faster in-game actuations and assured tactile feedback.");

    private final String string;

    ProductRazerMouse(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}