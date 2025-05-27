package org.fastcampus.user.repository;

import lombok.AllArgsConstructor;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        entity = jpaUserRepository.save(entity);
        return entity.toDomain();
    }

    @Override
    public User findById(Long id) {
        UserEntity userEntity = jpaUserRepository
            .findById(id)
            .orElseThrow(IllegalArgumentException::new);
        return userEntity.toDomain();
    }
}
