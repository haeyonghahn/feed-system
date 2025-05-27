package org.fastcampus.post.domain.content;

import lombok.Getter;

@Getter
public abstract class Content {

    String contentText;
    final DatetimeInfo datetimeInfo;

    public Content(String contentText) {
        checkLength(contentText);
        this.contentText = contentText;
        this.datetimeInfo = new DatetimeInfo();
    }

    public void updateContent(String contentText) {
        checkLength(contentText);
        this.contentText = contentText;
        this.datetimeInfo.updateEditDatetime();
    }

    protected abstract void checkLength(String contentText);

    public boolean isEdited() {
        return datetimeInfo.isEdited();
    }
}
