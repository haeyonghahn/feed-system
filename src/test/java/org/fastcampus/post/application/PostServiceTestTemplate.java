package org.fastcampus.post.application;

import org.fastcampus.common.FakeObjectFactory;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;

public class PostServiceTestTemplate {

    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();

    final User user = userService.createUser(new CreateUserRequestDto("user1", null));;
    final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));;

    CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);
    final Post post = postService.createPost(dto);
}
