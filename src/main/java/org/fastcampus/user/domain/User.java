package org.fastcampus.user.domain;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserInfo userInfo;
    private final UserRelationCount followingCount;
    private final UserRelationCount followerCount;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException("UserInfo cannot be null");
        }

        this.id = id;
        this.userInfo = userInfo;
        this.followingCount = new UserRelationCount();
        this.followerCount = new UserRelationCount();
    }

    public void follow(User followee) {
        if (this.equals(followee)) {
            throw new IllegalArgumentException("Cannot follow yourself");
        }

        followingCount.increaseCount();
        followee.increaseFollowerCount();
    }

    public void unfollow(User followee) {
        if (this.equals(followee)) {
            throw new IllegalArgumentException("Cannot unfollow yourself");
        }

        followingCount.decreaseCount();
        followee.decreaseFollowerCount();
    }

    private void increaseFollowerCount() {
        followerCount.increaseCount();
    }

    private void decreaseFollowerCount() {
        followerCount.decreaseCount();
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
