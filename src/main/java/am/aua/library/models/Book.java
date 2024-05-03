package am.aua.library.models;

import com.google.gson.annotations.Expose;

import java.net.URL;
import java.util.ArrayList;

/**
 * The Book class represents a book resource in the system.
 * It extends the Resource class and inherits its attributes and methods.
 */
public class Book implements Comparable<Book> {
    @Expose
    private Long isbn;
    @Expose
    private String title;
    @Expose
    private String subtitle;
    @Expose
    private String description;
    @Expose
    private int year;
    @Expose
    private int copies;
    @Expose
    private int pages;
    @Expose
    private String language;
    @Expose
    private String publisher;
    @Expose
    private URL cover;
    @Expose
    private ArrayList<Long> renters;
    @Expose
    private ArrayList<String> authors;
    @Expose
    private ArrayList<String> categories;

    public Book(
            Long isbn,
            String title,
            String subtitle,
            String description,
            int year,
            int copies,
            int pages,
            URL cover
    ) {
        setYear(year);
        setIsbn(isbn);
        this.pages = pages;
        this.cover = cover;
        this.title = title;
        this.copies = copies;
        this.subtitle = subtitle;
        this.description = description;
        this.authors = new ArrayList<>();
        this.renters = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public void addCategory(String category) {
        this.categories.add(category);
    }

    public void removeCategory(String category) {
        this.categories.remove(category);
    }

    private void setYear(int year) {
        if (year > 0) {
            this.year = year;
        }
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void incrementCopies() {
        this.copies++;
    }

    public void decrementCopies() {
        if (this.copies > 0) this.copies--;
    }

    public void setIsbn(Long isbn) {
        if ((isbn + "").length() == 13) {
            this.isbn = isbn;
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

    public ArrayList<Long> getRenters() {
        return new ArrayList<>(this.renters);
    }

    public ArrayList<Long> getRentersUnsafe() {
        return this.renters;
    }

    public Long getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void addAuthor(String author) {
        this.authors.add(author);
    }

    public void removeAuthor(String author) {
        this.authors.remove(author);
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

    public String getDescription() {
        return description;
    }
}
