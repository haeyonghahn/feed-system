package org.fastcampus.post.domain.content;

import java.time.LocalDateTime;
import lombok.Getter;

public class DatetimeInfo {

    private boolean isEdited;
    @Getter
    private LocalDateTime dateTime;

    public DatetimeInfo() {
        this.isEdited = false;
        this.dateTime = LocalDateTime.now();
    }

    public void updateEditDatetime() {
        this.isEdited = true;
        this.dateTime = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
    }
}
