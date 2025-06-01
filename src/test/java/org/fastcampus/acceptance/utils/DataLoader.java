package org.fastcampus.acceptance.utils;

import static org.fastcampus.acceptance.post.UserAcceptanceSteps.createUser;
import static org.fastcampus.acceptance.post.UserAcceptanceSteps.followUser;

import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    public void loadData() {
        // user 1, 2, 3 생성 및 user 1 follow user 2, user 3
        CreateUserRequestDto user = new CreateUserRequestDto("user", null);
        createUser(user);
        createUser(user);
        createUser(user);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(2L, 3L));
    }
}
