package org.fastcampus.post.repository.post_queue;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.dto.GetPostContentResponseDto;
import org.fastcampus.post.repository.entity.like.LikeTarget;
import org.fastcampus.post.repository.entity.like.QLikeEntity;
import org.fastcampus.post.repository.entity.post.QPostEntity;
import org.fastcampus.post.repository.entity.post.QUserPostQueueEntity;
import org.fastcampus.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl {

    private final JPAQueryFactory queryFactory;
    private static final QUserPostQueueEntity userPostQueueEntity = QUserPostQueueEntity.userPostQueueEntity;
    private static final QPostEntity postEntity = QPostEntity.postEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QLikeEntity likeEntity = QLikeEntity.likeEntity;

    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastContentId) {
        return queryFactory
            .select(
                Projections.fields(
                    GetPostContentResponseDto.class,
                    postEntity.id.as("id"),
                    postEntity.content.as("content"),
                    userEntity.id.as("userId"),
                    userEntity.name.as("userName"),
                    userEntity.profileImage.as("userProfileImage"),
                    postEntity.regDt.as("createdAt"),
                    postEntity.updDt.as("updatedAt"),
                    postEntity.commentCounter.as("commentCount"),
                    postEntity.likeCounter.as("likeCount"),
                    likeEntity.isNotNull().as("isLikedByMe")
                )
            )
            .from(userPostQueueEntity)
            .join(postEntity).on(userPostQueueEntity.postId.eq(postEntity.id))
            .join(userEntity).on(userPostQueueEntity.authorId.eq(userEntity.id))
            .leftJoin(likeEntity).on(hasLike(userId))
            .where(
                userPostQueueEntity.userId.eq(userId),
                hasLastData(lastContentId)
            )
            .orderBy(userPostQueueEntity.postId.desc())
            .limit(20)
            .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }

        return postEntity.id.lt(lastId);
    }

    private BooleanExpression hasLike(Long userId) {
        if (userId == null) {
            return null;
        }

        return postEntity.id
            .eq(likeEntity.id.targetId)
            .and(likeEntity.id.targetType.eq(LikeTarget.POST.name()))
            .and(likeEntity.id.userId.eq(userId));
    }
}
