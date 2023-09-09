package peaksoft.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.models.Lesson;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TaskResponse {
    private Long id;
    private String taskName;
    private String taskText;
    private ZonedDateTime deadline;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public TaskResponse(Long id, String taskName, String taskText, ZonedDateTime deadline, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
