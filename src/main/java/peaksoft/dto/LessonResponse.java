package peaksoft.dto;


import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Course;
import peaksoft.models.Task;

import java.time.ZonedDateTime;
import java.util.List;
@Getter
@Setter

public class LessonResponse {
    private Long id;
    private String lessonName;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public LessonResponse(Long id, String lessonName, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.lessonName = lessonName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
