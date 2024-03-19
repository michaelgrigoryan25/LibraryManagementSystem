package resource;

import user.User;

import java.util.Date;

public class Article extends Resource {

    public Article(String title, String content, User publishedBy, Date publishedAt, ResourceType resourceType) {
        super(title, content, publishedBy, publishedAt, resourceType);
    }
}
