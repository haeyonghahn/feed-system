package org.fastcampus.post.application.interfaces;

import java.util.Optional;
import org.fastcampus.post.domain.Post;

public interface PostRepository {

    Optional<Post> findById(Long id);
    Post save(Post post);
}
