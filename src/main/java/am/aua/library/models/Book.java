package am.aua.library.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Book class represents a book resource in the system.
 * It extends the Resource class and inherits its attributes and methods.
 */
public final class Book implements Comparable<Book> {

    @Expose
    private Long id;
    @Expose
    private final String title;
    @Expose
    private final String subtitle;
    @Expose
    private final int year;
    @Expose
    private int copies;
    @Expose
    private final int pages;
    @Expose
    private final String language;
    @Expose
    private final String publisher;
    @Expose
    private final List<Long> renters;
    @Expose
    private final List<String> authors;
    @Expose
    private final List<String> categories;

    /**
     * Constructs a Book object with the given parameters.
     *
     * @param id         The unique identifier of the book.
     * @param title      The title of the book.
     * @param subtitle   The subtitle of the book.
     * @param year       The publication year of the book.
     * @param copies     The number of copies available.
     * @param pages      The number of pages in the book.
     * @param language   The language of the book.
     * @param publisher  The publisher of the book.
     * @param renters    The list of renters who have rented the book.
     * @param authors    The list of authors of the book.
     * @param categories The list of categories the book belongs to.
     */
    public Book(Long id, String title, String subtitle, int year, int copies, int pages, String language, String publisher, List<Long> renters, List<String> authors, List<String> categories) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.year = year;
        this.copies = copies;
        this.pages = pages;
        this.language = language;
        this.publisher = publisher;
        this.renters = renters;
        this.authors = authors;
        this.categories = categories;
    }

    /**
     * Gets the subtitle of the book.
     *
     * @return The subtitle of the book.
     */
    public String getSubtitle() {
        return this.subtitle;
    }

    /**
     * Increments the number of copies available.
     */
    public void incrementCopies() {
        this.copies++;
    }

    /**
     * Decrements the number of copies available.
     */
    public void decrementCopies() {
        if (this.copies > 0) this.copies--;
    }

    /**
     * Gets the list of renters who have rented the book (unsafe).
     *
     * @return The list of renters.
     */
    public List<Long> getRentersUnsafe() {
        return this.renters;
    }

    /**
     * Gets a copy of the list of renters who have rented the book.
     *
     * @return A copy of the list of renters.
     */
    public List<Long> getRenters() {
        return new ArrayList<>(this.renters);
    }

    /**
     * Sets the ID of the book.
     *
     * @param id The ID to set.
     */
    public void setId(Long id) {
        if (id != null && id > 0) {
            this.id = id;
        }
    }

    /**
     * Adds a renter to the list of renters.
     *
     * @param id The ID of the renter to add.
     */
    public void addRenter(long id) {
        if (!renters.contains(id)) {
            decrementCopies();
            renters.add(id);
        }
    }

    /**
     * Removes a renter from the list of renters.
     *
     * @param id The ID of the renter to remove.
     */
    public void returnBook(long id) {
        if (renters.contains(id)) {
            incrementCopies();
            renters.remove(id);
        }
    }

    /**
     * Gets the authors of the book as a concatenated string.
     *
     * @return The authors of the book.
     */
    public String getAuthors() {
        return String.join(",", Objects.requireNonNullElse(this.authors, new ArrayList<>()));
    }

    /**
     * Gets the ID of the book.
     *
     * @return The ID of the book.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the publication year of the book.
     *
     * @return The publication year of the book.
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the number of pages in the book.
     *
     * @return The number of pages in the book.
     */
    public int getPages() {
        return pages;
    }

    /**
     * Gets the number of copies available.
     *
     * @return The number of copies available.
     */
    public int getCopies() {
        return this.copies;
    }

    /**
     * Compares this book with another book based on title and year.
     *
     * @param o The other book to compare to.
     * @return An integer representing the comparison result.
     */
    @Override
    public int compareTo(Book o) {
        int diff = this.getTitle().compareTo(o.getTitle());
        if (diff == 0) return Integer.compare(this.getYear(), o.getYear());
        return diff;
    }

    /**
     * Gets a string representation of the book.
     *
     * @return A string representation of the book.
     */
    @Override
    public String toString() {
        return this.title;
    }

    /**
     * Gets the language of the book.
     *
     * @return The language of the book.
     */
    public String getLanguage() {
        return language;
    }
}
