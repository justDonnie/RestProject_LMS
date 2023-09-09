package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.CourseRequest;
import peaksoft.dto.CourseResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.services.CourseService;

import java.util.List;




@RestController
@RequestMapping("/api/courses/{compId}")
@RequiredArgsConstructor
@Tag(name = "Course API")
public class CourseAPI {
    private final CourseService courseService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveCourses(@RequestBody CourseRequest courseRequest,
                                      @PathVariable Long compId){
        courseService.saveCourse(courseRequest,compId);
     return SimpleResponse.builder()
             .httpStatus(HttpStatus.OK)
             .message("Course is successfully  saved!!!")
             .build();
    }

    @PermitAll
    @GetMapping
    public List<CourseResponse> getAllCourses(@PathVariable Long compId){
        return courseService.getAllCourses(compId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/get/{courseId}")
    public CourseResponse findCourseById(@PathVariable Long courseId,@PathVariable Long compId){
        return courseService.findCourseByCompId(courseId,compId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("/put/{courseId}")
    public SimpleResponse updateCourseByCompId(@PathVariable Long courseId,
                                               @RequestBody CourseRequest courseRequest,
                                               @PathVariable Long compId){
        return courseService.updateCourse(courseId,courseRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{courseId}")
    public SimpleResponse deleteCourseById(@PathVariable Long courseId,
                                           @PathVariable Long compId){
        return courseService.deleteCourse(courseId);
    }
}










