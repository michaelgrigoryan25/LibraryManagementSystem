package models;

import java.util.Date;

public class Resource {

    private String title;
    private String content;
    private User publishedBy;
    private Date publishedAt;
    private ResourceType resourceType;

    public Resource(String title, String content, User publishedBy, Date publishedAt, ResourceType resourceType) {
        this.title = title;
        this.content = content;
        this.publishedBy = publishedBy;
        this.publishedAt = publishedAt;
        this.resourceType = resourceType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(User publishedBy) {
        this.publishedBy = publishedBy;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
}
