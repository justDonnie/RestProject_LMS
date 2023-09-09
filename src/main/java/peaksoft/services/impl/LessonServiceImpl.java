package peaksoft.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.LessonRequest;
import peaksoft.dto.LessonResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Course;
import peaksoft.models.Lesson;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.LessonRepository;
import peaksoft.services.LessonService;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class LessonServiceImpl implements LessonService{
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public SimpleResponse saveLesson(LessonRequest lsnRequest, Long crsId) {
        Course course = courseRepository.findById(crsId)
                .orElseThrow(() -> new NotFoundException(" Course with ID " + crsId + " is not found !!!"));
        Lesson lesson = new Lesson();
        lesson.setLessonName(lsnRequest.getLessonName());
        lesson.setCreatedAt(ZonedDateTime.now());
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Lesson with ID %s is successfully saved to course with ID %s !!!",lesson.getId(),crsId)
        );
    }

    @Override
    public List<LessonResponse> getAllLessons(Long crsId){
        List<LessonResponse>lessonResponses = lessonRepository.getAllLessons(crsId);
        if(lessonResponses.isEmpty()){
            throw new NotFoundException("Lessons with Course ID "+crsId+" are not found!!!");
        }
        return lessonResponses;
    }

    @Override
    public LessonResponse getLessonById(Long lsnId){
        return lessonRepository.getLessonById(lsnId);
    }

    @Override
    public SimpleResponse updateLesson(Long lsnId, Long crsId, LessonRequest lessonRequest) {
        Lesson lesson = lessonRepository.findById(lsnId).orElseThrow(() -> new NotFoundException("Lesson with ID " + lsnId + " is not found!!!"));

        Course course = courseRepository.findCourseById(crsId).orElseThrow(() -> new NotFoundException(" Course with ID " + crsId + " is not found!"));
        List<Course>courses = new ArrayList<>();
        courses.add(course);
        lesson.setCourse(course);
        lesson.setLessonName(lessonRequest.getLessonName());
        lesson.setUpdatedAt(ZonedDateTime.now());
        lessonRepository.save(lesson);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Lesson with ID %s is successfully updated!!!",lesson.getId())
        );
    }

    @Override
    public SimpleResponse deleteLesson(Long lsnId,Long crsId) {
        Course course = courseRepository.findById(crsId).orElseThrow(() -> new NotFoundException(" Course with ID " + crsId + " is not found!"));
        Lesson lesson = lessonRepository.findById(lsnId).orElseThrow(() -> new NotFoundException("Lesson with ID " + lsnId + " is not found!!!"));
        lesson.setCourse(course);
        lessonRepository.delete(lesson);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Lesson with ID %s is successfully deleted!!!",lesson.getId())
        );
    }
}
