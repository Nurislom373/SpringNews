package org.khasanof.junit5spring.classes;

/**
 * Author: Nurislom
 * <br/>
 * Date: 16.05.2023
 * <br/>
 * Time: 9:25
 * <br/>
 * Package: org.khasanof.junit5spring.classes
 */
public class Book {

    private final String title;

    private Book(String title) {
        this.title = title;
    }

    public static Book fromTitle(String title) {
        return new Book(title);
    }

    public String getTitle() {
        return this.title;
    }

}
