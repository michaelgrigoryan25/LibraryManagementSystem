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

    public String getSubtitle() {
        return this.subtitle;
    }

    public void incrementCopies() {
        this.copies++;
    }

    public void decrementCopies() {
        if (this.copies > 0) this.copies--;
    }

    public void setId(Long id) {
        if (id != null && id > 0) {
            this.id = id;
        }
    }

    public void addRenter(long id) {
        if (!renters.contains(id)) {
            decrementCopies();
            renters.add(id);
        }
    }

    public void returnBook(long id) {
        if (renters.contains(id)) {
            incrementCopies();
            renters.remove(id);
        }
    }

    public String getAuthors() {
        return String.join(",", Objects.requireNonNullElse(this.authors, new ArrayList<>()));
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public int getCopies() {
        return this.copies;
    }

    @Override
    public int compareTo(Book o) {
        int diff = this.getTitle().compareTo(o.getTitle());
        if (diff == 0) return Integer.compare(this.getYear(), o.getYear());
        return diff;
    }

    @Override
    public String toString() {
        return this.title;
    }

    public String getLanguage() {
        return language;
    }
}
