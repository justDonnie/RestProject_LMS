package peaksoft.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.InstructorResponse;
import peaksoft.dto.InstructorsFullInfo;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UniRegistrRequest;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Company;
import peaksoft.models.Course;
import peaksoft.models.Group;
import peaksoft.models.Instructor;
import peaksoft.repositories.CompanyRepository;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.GroupRepository;
import peaksoft.repositories.InstructorRepository;
import peaksoft.services.InstructorService;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class InstructorServiceImpl implements InstructorService{
    private final CompanyRepository companyRepository;
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;
    private final GroupRepository groupRepository;


    @Override
    public SimpleResponse saveInstructor(UniRegistrRequest uniRegistrRequest) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(uniRegistrRequest.firstName());
        instructor.setLastName(uniRegistrRequest.lastName());
        instructor.setPhoneNumber(uniRegistrRequest.phoneNumber());
        instructor.setEmail(uniRegistrRequest.email());
        instructor.setPassword(passwordEncoder.encode(uniRegistrRequest.password()));
        instructor.setSpecialization(uniRegistrRequest.specialization());
        instructor.setCreatedAt(ZonedDateTime.now());
        instructorRepository.save(instructor);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Instructor with ID %s is successfully saved!!!",instructor.getId())
        );
    }

    @Override
    public List<InstructorResponse> getAllInstructors() {
        return instructorRepository.getAllInstructors();
    }

    @Override
    public InstructorResponse getInstructorById(Long instId) {
        return instructorRepository.getInstructorById(instId)
                .orElseThrow(()->new NotFoundException("Instructor with ID "+instId+" is not found!!!"));
    }

    @Override
    public SimpleResponse updateInstructor(Long instId, UniRegistrRequest uniRegistrRequest) {
        Instructor instructor = instructorRepository.findById(instId)
                .orElseThrow(() -> new NotFoundException("Instructor with ID " + instId + " is not found!!!"));
        instructor.setFirstName(uniRegistrRequest.firstName());
        instructor.setLastName(uniRegistrRequest.lastName());
        instructor.setPhoneNumber(uniRegistrRequest.phoneNumber());
        instructor.setEmail(uniRegistrRequest.email());
        instructor.setPassword(passwordEncoder.encode(uniRegistrRequest.password()));
        instructor.setSpecialization(uniRegistrRequest.specialization());
        instructor.setUpdatedAt(ZonedDateTime.now());
        instructorRepository.save(instructor);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Instructor with ID %s is successfully updated!!!",instructor.getId())
        );
    }

    @Override
    public SimpleResponse deleteInstructor(Long instId) {
        Instructor instructor = instructorRepository.findById(instId)
                .orElseThrow(() -> new NotFoundException("Not found instructor with id: " + instId));
        List<Company> companies =new ArrayList<>(instructor.getCompanies());
        for(Company c:companies){
            c.getInstructors().remove(instructor);
            instructor.getCompanies().remove(c);
        }
        instructorRepository.delete(instructor);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Instructor with ID %s is successfully deleted!!!",instructor.getId())
        );
    }

    @Override
    public SimpleResponse assignInstToComp(Long instId, Long compId) {
        Company company = companyRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException(" Company with ID " + compId + " is not found !!!"));
        Instructor instructor = instructorRepository.findById(instId)
                .orElseThrow(() -> new NotFoundException(" Instructor with ID " + instId + " is not found!!!"));
        company.getInstructors().add(instructor);
        instructor.getCompanies().add(company);
        instructorRepository.save(instructor);
        companyRepository.save(company);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Instructor with ID %s is successfully assigned !!!",instructor.getId())
        );
    }

    @Override
    public SimpleResponse assignInstToCourse(Long instId, Long courseId) {
        Instructor instructor = instructorRepository.findById(instId).orElseThrow(() -> new NotFoundException(" Instructor with ID " + instId + " is not found!!!"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException(" Course with ID " + courseId + " is not found!!!"));
        instructor.getCourses().add(course);
        course.setInstructor(instructor);
        instructorRepository.save(instructor);
        courseRepository.save(course);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Instructor with ID %s is successfully assigned !!!",instructor.getId())
        );
    }

    @Override
    public InstructorsFullInfo getInstructorInfo(Long instId) {
        Instructor instructor = instructorRepository.findById(instId).orElseThrow(() -> new NotFoundException("Instructor with ID" + instId + " is not found!!!"));
        Group group = groupRepository.findById(instructor.getId()).orElseThrow(() -> new NotFoundException("Group is not found!!!"));
        int numberOfStudents = group.getStudents().size();
        return new InstructorsFullInfo(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                group.getGroupName(),
                numberOfStudents
        );
    }


}
