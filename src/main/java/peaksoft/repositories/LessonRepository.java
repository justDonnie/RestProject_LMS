package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.LessonResponse;
import peaksoft.models.Lesson;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select new peaksoft.dto.LessonResponse(l.id,l.lessonName,l.createdAt,l.updatedAt) from Lesson l where l.course.id=:crsId")
    List<LessonResponse> getAllLessons(Long crsId);

    @Query("select new peaksoft.dto.LessonResponse(l.id,l.lessonName,l.createdAt,l.updatedAt) from Lesson l where l.id=:lessonId")
    LessonResponse getLessonById(Long lessonId);


}