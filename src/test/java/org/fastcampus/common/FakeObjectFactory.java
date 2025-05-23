package org.fastcampus.common;

import org.fastcampus.user.application.UserRelationService;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;

public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(fakeUserRelationRepository, userService);

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }
}
