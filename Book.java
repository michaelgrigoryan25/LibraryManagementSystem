import java.util.ArrayList;
import java.util.Set;

/**
 * The <code>Book</code> class provides useful abstraction for storing
 * different types of information related to books. It also provides
 * useful methods for interacting with the books.
 */
public class Book {
    private String doi;
    private String isbn;
    private String title;
    private String publisher;
    private String publisherCity;
    private int publicationDay;
    private int publicationMonth;
    private int publicationYear;
    private ArrayList<String> authors;
    private Set<Genre> genres;
}
