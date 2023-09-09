package peaksoft.dto;


import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Lesson;

import java.time.ZonedDateTime;

@Getter
@Setter

public class TaskRequest {
    private String taskName;
    private String taskText;
    private ZonedDateTime deadline;
}
