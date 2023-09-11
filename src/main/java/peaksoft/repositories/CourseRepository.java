package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.CourseResponse;
import peaksoft.models.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select new peaksoft.dto.CourseResponse"+
            "(c.id,c.courseName,c.dateOfStart,c.description," +
            "c.createdAt,c.updatedAt)" +
            " from Course c inner join Company p on p.id = c.company.id where p.id=:compId")
    List<CourseResponse> getAllCourses(@Param("compId") Long compId);

    @Query("select new peaksoft.dto.CourseResponse(c.id,c.courseName,c.dateOfStart,c.description,c.createdAt,c.updatedAt) from Course c join Company p on c.company.id = p.id where p.id=:compId and c.id=:courseId")
    Optional<CourseResponse>findCourseByCompId(Long courseId,Long compId);

    @Query("select c from Company p join Course c on p.id=c.company.id where c.id=:courseId")
    Optional<Course>findCourseById(Long courseId);

    @Query("select new peaksoft.dto.CourseResponse(c.id,c.courseName,c.dateOfStart,c.description,c.createdAt,c.updatedAt) from Course c order by c.createdAt asc")
    List<CourseResponse> sortCourseByDateAsc();

    @Query("select new peaksoft.dto.CourseResponse(c.id,c.courseName,c.dateOfStart,c.description,c.createdAt,c.updatedAt) from Course c order by c.createdAt desc")
    List<CourseResponse> sortCourseByDateDesc();
}
