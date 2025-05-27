package org.fastcampus.post.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.fastcampus.post.repository.jpa.JpaCommentRepository;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;

    @Override
    public Comment findById(Long id) {
        CommentEntity entity = jpaCommentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        return entity.toDomain();
    }

    @Transactional
    @Override
    public Comment save(Comment comment) {
        Post targetPost = comment.getPost();
        CommentEntity entity = jpaCommentRepository.save(new CommentEntity(comment));
        jpaPostRepository.increaseCommentCounter(targetPost.getId());
        return entity.toDomain();
    }
}
