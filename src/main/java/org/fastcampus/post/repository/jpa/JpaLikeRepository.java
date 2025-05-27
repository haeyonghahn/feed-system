package org.fastcampus.post.repository.jpa;

import org.fastcampus.post.repository.entity.like.LikeEntity;
import org.fastcampus.post.repository.entity.like.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeId> {
}
