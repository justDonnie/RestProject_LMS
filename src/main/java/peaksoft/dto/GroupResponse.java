package peaksoft.dto;


import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Course;
import peaksoft.models.Student;

import java.time.ZonedDateTime;
import java.util.List;
@Getter
@Setter

public class GroupResponse {
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public GroupResponse(Long id, String groupName, String imageLink, String description, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.groupName = groupName;
        this.imageLink = imageLink;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
