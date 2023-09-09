package peaksoft.services;

import peaksoft.dto.LessonRequest;
import peaksoft.dto.LessonResponse;
import peaksoft.dto.SimpleResponse;

import java.util.List;

public interface LessonService {

    SimpleResponse saveLesson(LessonRequest lsnRequest, Long crsId);

    List<LessonResponse> getAllLessons(Long crsId);

    LessonResponse getLessonById(Long lsnId);

    SimpleResponse updateLesson(Long lsnId,Long crsId, LessonRequest lessonRequest);

    SimpleResponse deleteLesson(Long lsnId, Long crsId);

}
