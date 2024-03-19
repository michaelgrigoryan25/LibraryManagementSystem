package models;

import java.util.Date;

public class Book extends Resource {

    public Book(String title, String content, User publishedBy, Date publishedAt, ResourceType resourceType) {
        super(title, content, publishedBy, publishedAt, resourceType);
    }
}
