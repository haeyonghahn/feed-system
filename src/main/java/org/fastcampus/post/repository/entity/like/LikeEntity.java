package org.fastcampus.post.repository.entity.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.repository.TimeBaseEntity;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.domain.User;

@Entity
@Table(name = "community_like")
@NoArgsConstructor
@Getter
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeId id;

    public LikeEntity(Post post, User likedUser) {
        this.id = new LikeId(post.getId(), likedUser.getId(), LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likedUser) {
        this.id = new LikeId(comment.getId(), likedUser.getId(), LikeTarget.COMMENT.name());
    }
}
