package org.fastcampus.post.domain;

import java.util.Objects;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private PostPublicationState state;
    private final PositiveIntegerCounter likeCount;

    public Post(Long id, User author, Content content, PostPublicationState state, PositiveIntegerCounter likeCount) {
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
        this.likeCount = likeCount;
    }

    public Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC, new PositiveIntegerCounter());
    }

    public Post(Long id, User author, String content) {
        this(id, author, new PostContent(content), PostPublicationState.PUBLIC, new PositiveIntegerCounter());
    }

    public void updateContent(User user, String content) {
        if (!author.equals(user)) {
            throw new IllegalArgumentException("Only the author can update the content");
        }
        this.content.updateContent(content);
    }

    public void like(User user) {
        if (author.equals(user)) {
            throw new IllegalArgumentException("author cannot like own post");
        }
        likeCount.increase();
    }

    public void unlike() {
        likeCount.decrease();
    }

    public void updateState(PostPublicationState state) {
        this.state = state;
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
        return likeCount.getCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
