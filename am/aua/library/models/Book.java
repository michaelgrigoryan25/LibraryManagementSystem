package am.aua.library.models;

import java.util.Date;

/**
 * The Book class represents a book resource in the system.
 * It extends the Resource class and inherits its attributes and methods.
 */
public class Book extends Resource {

    /**
     * Constructs a new Book object with the specified attributes.
     *
     * @param title       the title of the book
     * @param content     the content of the book
     * @param publishedBy the user who published the book
     * @param publishedAt the date when the book was published
     */
    public Book(String title, String content, User publishedBy, Date publishedAt) {
        super(title, content, publishedBy, publishedAt);
    }
}
