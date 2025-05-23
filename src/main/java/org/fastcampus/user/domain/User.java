package org.fastcampus.user.domain;

import java.util.Objects;
import org.fastcampus.common.PositiveIntegerCounter;

public class User {

    private final Long id;
    private final UserInfo userInfo;
    private final PositiveIntegerCounter followingCount;
    private final PositiveIntegerCounter followerCount;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException("UserInfo cannot be null");
        }

        this.id = id;
        this.userInfo = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCount = new PositiveIntegerCounter();
    }

    public void follow(User followee) {
        if (this.equals(followee)) {
            throw new IllegalArgumentException("Cannot follow yourself");
        }

        followingCount.increase();
        followee.increaseFollowerCount();
    }

    public void unfollow(User followee) {
        if (this.equals(followee)) {
            throw new IllegalArgumentException("Cannot unfollow yourself");
        }

        followingCount.decrease();
        followee.decreaseFollowerCount();
    }

    private void increaseFollowerCount() {
        followerCount.increase();
    }

    private void decreaseFollowerCount() {
        followerCount.decrease();
    }

    public int getFollowingCount() {
        return followingCount.getCount();
    }

    public int getFollowerCount() {
        return followerCount.getCount();
    }

    public Long getId() {
        return id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
