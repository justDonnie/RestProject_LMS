package peaksoft.services;

import peaksoft.dto.CourseRequest;
import peaksoft.dto.CourseResponse;
import peaksoft.dto.SimpleResponse;

import java.util.List;

public interface CourseService {

    SimpleResponse saveCourse(CourseRequest courseRequest,Long compId);

    List<CourseResponse> getAllCourses(Long compId);

    CourseResponse findCourseByCompId(Long courseId,Long compId);

    SimpleResponse updateCourse(Long courseId, CourseRequest newCourseRequest);

    SimpleResponse deleteCourse(Long courseId);
}
