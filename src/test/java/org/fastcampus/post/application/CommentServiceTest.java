package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.common.FakeObjectFactory;
import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.Content;
import org.junit.jupiter.api.Test;

class CommentServiceTest extends PostServiceTestTemplate {

    private final CommentService commentService = FakeObjectFactory.getCommentService();
    private final String commentContent = "this is test comment";

    CreateCommentRequestDto dto = new CreateCommentRequestDto(post.getId(), user.getId(), commentContent);

    @Test
    void givenCreateCommentRequestDtoWhenCreateCommentThenReturnComment() {
        // when
        Comment comment = commentService.createComment(dto);

        // then
        Content content = comment.getContent();
        assertEquals(commentContent, content.getContentText());
    }

    @Test
    void givenCreateCommentWhenUpdateCommentThenReturnUpdatedComment() {
        // given
        Comment comment = commentService.createComment(dto);

        // when
        String updatedCommentContent = "this is updated comment";
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(comment.getId(),
            user.getId(), updatedCommentContent);
        Comment updatedComment = commentService.updateComment(updateCommentRequestDto);

        // then
        Content content = updatedComment.getContent();
        assertEquals(updatedCommentContent, content.getContentText());
    }

    @Test
    void givenCommentWhenLikeCommentThenReturnCommentWithLike() {
        // given
        Comment comment = commentService.createComment(dto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(otherUser.getId(), comment.getId());
        commentService.likeComment(likeRequestDto);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentWhenUnlikeCommentThenReturnCommentWithoutLike() {
        // given
        Comment comment = commentService.createComment(dto);

        // when
        LikeRequestDto likeRequestDto = new LikeRequestDto(otherUser.getId(), comment.getId());
        commentService.likeComment(likeRequestDto);
        commentService.unlikeComment(likeRequestDto);

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentWhenLikeSelfThenThrowException() {
        // given
        Comment comment = commentService.createComment(dto);

        // when, then
        LikeRequestDto likeRequestDto = new LikeRequestDto(user.getId(), comment.getId());
        assertThrows(IllegalArgumentException.class, () -> commentService.likeComment(likeRequestDto));
    }
}
