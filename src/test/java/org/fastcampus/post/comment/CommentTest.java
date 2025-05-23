package org.fastcampus.post.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class CommentTest {

    private final User user = new User(1L, new UserInfo("name", "url"));
    private final User otherUser = new User(2L, new UserInfo("name", "url"));

    private final Post post = new Post(1L, user, "content");
    private final Comment comment = new Comment(1L, post, user, "comment content");

    @Test
    void givenCommentWhenLikeThenLikeCountShouldBe1() {
        // when
        comment.like(otherUser);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentWhenLikeBySameUserThenLikeCountShouldThrowError() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreatedAndLikeWhenUnlikeThenLikeCountShouldBe0() {
        // given
        comment.getLikeCount();

        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreatedWhenUnlikeThenLikeCountShouldBe0() {
        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentWhenUpdateContentThenContentShouldBeUpdated() {
        // given
        String newCommentContent = "new comment content";

        // when
        comment.updateContent(user, newCommentContent);

        // then
        assertEquals(newCommentContent, comment.getContent().getContentText());
    }

    @Test
    void givenCommentWhenUpdateContentOver100ThenThrowError() {
        // given
        String newContent = "a".repeat(101);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateContent(user, newContent));
    }
}
