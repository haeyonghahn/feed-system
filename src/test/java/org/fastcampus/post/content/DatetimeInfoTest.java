package org.fastcampus.post.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.fastcampus.post.domain.content.DatetimeInfo;
import org.junit.jupiter.api.Test;

class DatetimeInfoTest {

    @Test
    void givenCreatedWhenUpdateThenEditedTrueAndTimeIsUpdated() {
        // given
        DatetimeInfo info = new DatetimeInfo();
        LocalDateTime datetime = info.getDateTime();

        // when
        info.updateEditDatetime();

        // then
        assertEquals(datetime, info.getDateTime());
        assertTrue(info.isEdited());
    }
}
