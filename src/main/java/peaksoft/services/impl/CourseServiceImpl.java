package peaksoft.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.CourseRequest;
import peaksoft.dto.CourseResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Company;
import peaksoft.models.Course;
import peaksoft.repositories.CompanyRepository;
import peaksoft.repositories.CourseRepository;
import peaksoft.services.CourseService;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    @Override
    public SimpleResponse saveCourse(CourseRequest courseRequest, Long compId) {
        Company company = companyRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Company with ID" + compId + " is not found!!!"));
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDateOfStart(courseRequest.getDateOfStart());
        course.setDescription(courseRequest.getDescription());
        course.setCreatedAt(ZonedDateTime.now());
        course.setCompany(company);
        courseRepository.save(course);
        log.info("Course successfully saved!!!");
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Course with ID " + course.getId() + " is successfully saved!!!")
        );
    }

    @Override
    public List<CourseResponse> getAllCourses(Long compId) {
        List<CourseResponse> courses = courseRepository.getAllCourses(compId);
        if (courses.isEmpty()) {
            throw new NotFoundException("Company with ID " + compId + " is not found!!!");
        }
        return courses;
    }

    @Override
    public CourseResponse findCourseByCompId(Long courseId,Long compId) {
        return courseRepository.findCourseByCompId(courseId,compId)
                .orElseThrow(() -> new NotFoundException("Course with ID " + courseId + " is not found in company with ID "+compId+" !!!"));
    }

    @Override
    public SimpleResponse updateCourse(Long courseId, CourseRequest newCourseRequest) {
        Course course = courseRepository.findCourseById(courseId).orElseThrow(() -> new NotFoundException("Course with ID " + courseId + " is not found!!!"));
        course.setCourseName(newCourseRequest.getCourseName());
        course.setDateOfStart(newCourseRequest.getDateOfStart());
        course.setDescription(newCourseRequest.getDescription());
        course.setUpdatedAt(ZonedDateTime.now());
        courseRepository.save(course);
        log.info(" Course is successfully is updated!!!");
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Course with ID " + course.getId() + " is successfully updated!!!")
        );
    }

    @Override
    public SimpleResponse deleteCourse(Long courseId) {
        Course course = courseRepository.findCourseById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with ID " + courseId + " is not found!!!"));
        if(!courseRepository.existsById(courseId)){
            throw new NotFoundException("Course with ID " + courseId + " is not found!!!");
        }
        courseRepository.delete(course);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Course is successfully deleted!!!")
        );
    }
}
