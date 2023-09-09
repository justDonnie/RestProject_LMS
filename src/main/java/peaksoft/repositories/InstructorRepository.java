package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.InstructorResponse;
import peaksoft.models.Instructor;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("select new peaksoft.dto.InstructorResponse(i.id,i.firstName,i.lastName,i.phoneNumber,i.specialization,i.email,i.password,i.createdAt,i.updatedAt)from Instructor i")
    List<InstructorResponse> getAllInstructors();

    @Query("select new peaksoft.dto.InstructorResponse(i.id,i.firstName,i.lastName,i.phoneNumber,i.specialization,i.email,i.password,i.createdAt,i.updatedAt) from Instructor i where i.id=:instId ")
    Optional<InstructorResponse>getInstructorById(Long instId);
}