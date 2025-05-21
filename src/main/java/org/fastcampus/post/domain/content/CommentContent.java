package org.fastcampus.post.domain.content;

public class CommentContent extends Content {

    private static final int MAX_CONTENT_LENGTH = 100;

    public CommentContent(String contentText) {
        super(contentText);
    }

    @Override
    protected void checkText(String contentText) {
        if (contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be null or empty");
        }
        if (contentText.length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException(String.format("comment should be under %d", MAX_CONTENT_LENGTH));
        }
    }
}
