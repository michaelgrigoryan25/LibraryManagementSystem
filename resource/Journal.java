package resource;

import user.User;

import java.util.Date;

public class Journal extends Resource {

    public Journal(String title, String content, User publishedBy, Date publishedAt, ResourceType resourceType) {
        super(title, content, publishedBy, publishedAt, resourceType);
    }
}
