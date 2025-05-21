package org.fastcampus.post.domain.content;

public class PostContent extends Content {

    private static final int MIN_CONTENT_LENGTH = 5;
    private static final int MAX_CONTENT_LENGTH = 500;

    public PostContent(String contentText) {
        super(contentText);
    }

    @Override
    protected void checkText(String contentText) {
        if (contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException("Post content cannot be null or empty");
        }
        if (contentText.length() < MIN_CONTENT_LENGTH) {
            throw new IllegalArgumentException(String.format("post should be over %d", MIN_CONTENT_LENGTH));
        }
        if (contentText.length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException(String.format("post should be under %d", MAX_CONTENT_LENGTH));
        }
    }
}
