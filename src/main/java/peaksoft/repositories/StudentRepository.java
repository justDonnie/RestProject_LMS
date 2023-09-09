package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.StudentResponse;
import peaksoft.models.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select new peaksoft.dto.StudentResponse(s.id,s.firstName,s.lastName,s.phoneNumber,s.email,s.password,s.studyFormat,s.isBlocked,s.createdAt,s.updatedAt) from Student s")
    List<StudentResponse> getAllStudents();

    @Query("select new peaksoft.dto.StudentResponse(s.id,s.firstName,s.lastName,s.phoneNumber,s.email,s.password,s.studyFormat,s.isBlocked,s.createdAt,s.updatedAt) from Student s where s.id = :studentId")
    StudentResponse findStudentById(Long studentId);
}