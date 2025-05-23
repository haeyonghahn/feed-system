package org.fastcampus.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.common.FakeObjectFactory;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void givenUserInfoDtoWhenCreateUserThenCanFindUser() {
        // given
        CreateUserRequestDto dto = new CreateUserRequestDto("hahn", "www.naver.com");

        // when
        User savedUser = userService.createUser(dto);

        // then
        User foundUser = userService.getUser(savedUser.getId());
        assertEquals(savedUser, foundUser);
    }
}
