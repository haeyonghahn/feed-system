package org.fastcampus.post.domain;

import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.like.LikeCounter;
import org.fastcampus.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private final PostPublicationState state;
    private final LikeCounter likeCounter;

    public Post(Long id, User author, Content content, PostPublicationState state, LikeCounter likeCounter) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.state = state;
        this.likeCounter = likeCounter;
    }

    public Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC, new LikeCounter());
    }

    public PostPublicationState getState() {
        return state;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Content getContent() {
        return content;
    }

    public int getLikeCount() {
        return likeCounter.getLikeCount();
    }
}
