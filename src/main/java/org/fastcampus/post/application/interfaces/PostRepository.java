package org.fastcampus.post.application.interfaces;

import org.fastcampus.post.domain.Post;

public interface PostRepository {

    Post findById(Long id);
    Post save(Post post);
    Post publish(Post post);
}
