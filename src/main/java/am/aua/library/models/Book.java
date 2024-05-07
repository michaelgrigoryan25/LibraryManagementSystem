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
    private Long id;
    @Expose
    private String title;
    @Expose
    private String subtitle;
    //    @Expose
    //    private String description;
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
    //    @Expose
    //    private URL cover;
    //    @Expose
    //    private URL link;
    @Expose
    private ArrayList<Long> renters;
    @Expose
    private ArrayList<String> authors;
    @Expose
    private ArrayList<String> categories;

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

    public ArrayList<Long> getRenters() {
        return new ArrayList<>(this.renters);
    }

    public Long getId() {
        return this.id;
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
        String content = this.title;
        if (this.subtitle != null) content += this.subtitle;
        return content;
    }

//    public String getDescription() {
//        return description;
//    }

//    public URL getCover() {
//        return cover;
//    }

//    public URL getLink() {
//        return link;
//    }

    public String getLanguage() {
        return language;
    }
}
