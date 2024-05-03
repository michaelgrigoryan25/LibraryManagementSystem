package am.aua.library.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Date;

/**
 * The Book class represents a book resource in the system.
 * It extends the Resource class and inherits its attributes and methods.
 */
public class Book extends Resource implements Comparable<Book> {
    @Expose
    // The ids of users who have rented this book
    private ArrayList<Long> rentedBy;


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

    public boolean addRenter(long id) {
        if (!rentedBy.contains(id)) {
            decrementAvailableQuantity();
            rentedBy.add(id);
            return true;
        }

        return false;
    }

    public boolean returnBook(long id) {
        if (rentedBy.contains(id)) {
            incrementAvailableQuantity();
            rentedBy.remove(id);
            return true;
        }

        return false;
    }

    @Override
    public int compareTo(Book o) {
        return this.getTitle().compareTo(o.getTitle());
    }
}
