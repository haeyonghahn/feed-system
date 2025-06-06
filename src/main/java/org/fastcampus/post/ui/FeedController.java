package org.fastcampus.post.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.post.application.dto.GetPostContentResponseDto;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepositoryImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepositoryImpl userPostQueueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeedList(@PathVariable(name = "userId") Long userId, Long lastContentId) {
        List<GetPostContentResponseDto> contentResponse = userPostQueueQueryRepository.getContentResponse(userId, lastContentId);
        return Response.ok(contentResponse);
    }
}
