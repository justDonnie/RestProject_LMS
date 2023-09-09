package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Company;
import peaksoft.models.Group;
import peaksoft.models.Instructor;
import peaksoft.models.Lesson;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
@Getter
@Setter

public class CourseResponse {
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public CourseResponse(Long id, String courseName, LocalDate dateOfStart, String description, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.courseName = courseName;
        this.dateOfStart = dateOfStart;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
