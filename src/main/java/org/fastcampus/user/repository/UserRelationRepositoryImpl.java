package org.fastcampus.user.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.fastcampus.post.repository.post_queue.UserPostQueueCommandRepository;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.entity.UserRelationEntity;
import org.fastcampus.user.repository.entity.UserRelationId;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.fastcampus.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final UserPostQueueCommandRepository userPostQueueRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationId id = new UserRelationId(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Transactional
    @Override
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        userPostQueueRepository.saveFollowPost(user.getId(), targetUser.getId());
    }

    @Transactional
    @Override
    public void delete(User user, User targetUser) {
        UserRelationId id = new UserRelationId(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        userPostQueueRepository.deleteUnfollowPost(user.getId(), targetUser.getId());
    }
}
