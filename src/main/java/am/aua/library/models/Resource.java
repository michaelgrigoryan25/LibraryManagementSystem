package am.aua.library.models;

import com.google.gson.annotations.Expose;

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
    @Expose
    private Long id;
    /**
     * The title of the resource.
     */
    @Expose
    private String title;
    /**
     * The content of the resource.
     */
    @Expose
    private String content;
    /**
     * The user who published the resource.
     */
    @Expose
    private User publishedBy;
    /**
     * The date when the resource was published.
     */
    @Expose
    private Date publishedAt;
    // The number of current resources available
    @Expose
    private int availableQuantity;

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
        this.title = title.toUpperCase();
        this.content = content;
        this.availableQuantity = 0;
        this.publishedBy = publishedBy;
        this.publishedAt = publishedAt;
    }

    public int getAvailableQuantity() {
        return this.availableQuantity;
    }

    public void incrementAvailableQuantity() {
        this.availableQuantity++;
    }

    public void decrementAvailableQuantity() {
        if (this.availableQuantity > 0) {
            this.availableQuantity--;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id != null && id >= 0) {
            this.id = id;
        }
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
