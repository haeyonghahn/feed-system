package org.fastcampus.user.application;

import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;

public class UserRelationService {

    private final UserRelationRepository userRelationRepository;
    private final UserService userService;

    public UserRelationService(UserRelationRepository userRelationRepository,
        UserService userService) {
        this.userRelationRepository = userRelationRepository;
        this.userService = userService;
    }

    public void followUser(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("Already following the user");
        }

        user.follow(targetUser);

        userRelationRepository.save(user, targetUser);
    }

    public void unfollowUser(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (!userRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("Not following the user");
        }

        user.unfollow(targetUser);

        userRelationRepository.delete(user, targetUser);
    }
}
