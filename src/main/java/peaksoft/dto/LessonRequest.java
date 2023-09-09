package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter

public class LessonRequest {
    private String lessonName;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

}
