package peaksoft.dto;


import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Course;
import peaksoft.models.Student;

import java.util.List;


@Getter
@Setter

public class GroupRequest {
    private String groupName;
    private String imageLink;
    private String description;
}
