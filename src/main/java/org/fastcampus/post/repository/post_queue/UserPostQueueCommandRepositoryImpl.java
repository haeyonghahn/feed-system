package org.fastcampus.post.repository.post_queue;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.entity.post.UserPostQueueEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.fastcampus.post.repository.jpa.JpaUserPostQueueRepository;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Override
    public void publishPost(PostEntity postEntity) {
        UserEntity authorEntity = postEntity.getAuthor();
        List<Long> followers = jpaUserRelationRepository.findFollowers(authorEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntities = followers.stream()
            .map(userId -> new UserPostQueueEntity(postEntity.getId(), userId, authorEntity.getId()))
            .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    @Override
    public void saveFollowPost(Long userId, Long targetId) {
       List<Long> postIds = jpaPostRepository.findAllPostIdsByAuthorId(targetId);

       List<UserPostQueueEntity> userPostQueueEntities = postIds.stream()
           .map(postId -> new UserPostQueueEntity(postId, userId, targetId))
           .toList();

       jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    @Override
    public void deleteUnfollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
