package org.fastcampus.user.domain;

public class UserRelationCount {

    private int count;

    public UserRelationCount() {
        this.count = 0;
    }

    public void increaseCount() {
        this.count++;
    }

    public void decreaseCount() {
        if (this.count > 0) {
            this.count--;
        }
    }

    public int getCount() {
        return count;
    }
}
