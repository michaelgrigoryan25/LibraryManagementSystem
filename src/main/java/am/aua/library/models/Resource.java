package am.aua.library.models;

import java.util.Date;
import java.util.UUID;

/**
 * The Resource class represents a generic resource in the system.
 * It is an abstract class that contains common attributes and methods for all types of resources.
 */
public abstract class Resource {
    /**
     * The id of the resource.
     */
    private final long id;


    /**
     * The title of the resource.
     */
    private String title;
    /**
     * The content of the resource.
     */
    private String content;
    /**
     * The user who published the resource.
     */
    private User publishedBy;
    /**
     * The date when the resource was published.
     */
    private Date publishedAt;

    /**
     * Constructs a new Resource object with the specified attributes.
     *
     * @param title       the title of the resource
     * @param content     the content of the resource
     * @param publishedBy the user who published the resource
     * @param publishedAt the date when the resource was published
     */
    public Resource(String title, String content, User publishedBy, Date publishedAt) {
        this.id = UUID.randomUUID().getLeastSignificantBits();
        this.title = title;
        this.content = content;
        this.publishedBy = publishedBy;
        this.publishedAt = publishedAt;
    }

    /**
     * Gets the title of the resource.
     *
     * @return the title of the resource
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the resource.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content of the resource.
     *
     * @return the content of the resource
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the resource.
     *
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the user who published the resource.
     *
     * @return the user who published the resource
     */
    public User getPublishedBy() {
        return publishedBy;
    }

    /**
     * Sets the user who published the resource.
     *
     * @param publishedBy the user to set as the publisher
     */
    public void setPublishedBy(User publishedBy) {
        this.publishedBy = publishedBy;
    }

    /**
     * Gets the date when the resource was published.
     *
     * @return the date when the resource was published
     */
    public Date getPublishedAt() {
        return publishedAt;
    }

    /**
     * Sets the date when the resource was published.
     *
     * @param publishedAt the date to set as the publication date
     */
    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}
