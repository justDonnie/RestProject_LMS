package peaksoft.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StudentResponse;
import peaksoft.dto.UniRegistrRequest;
import peaksoft.exceptions.BadCredentialException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Group;
import peaksoft.models.Student;
import peaksoft.repositories.GroupRepository;
import peaksoft.repositories.StudentRepository;
import peaksoft.services.StudentService;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public SimpleResponse saveStudent(UniRegistrRequest uniRegistrRequest) {
        Student student = new Student();
        student.setFirstName(uniRegistrRequest.firstName());
        student.setLastName(uniRegistrRequest.lastName());
        student.setPhoneNumber(uniRegistrRequest.phoneNumber());
        student.setEmail(uniRegistrRequest.email());
        student.setPassword(passwordEncoder.encode(uniRegistrRequest.password()));
        student.setStudyFormat(uniRegistrRequest.studyFormat());
        student.setBlocked(student.isBlocked());
        student.setCreatedAt(ZonedDateTime.now());
        studentRepository.save(student);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Student with ID %s is successfully saved!!!",student.getId())
        );
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    @Override
    public StudentResponse getStudentById(Long stdId){
        return studentRepository.findStudentById(stdId);
    }

    @Override
    public SimpleResponse assignStudentToGroup(Long stdId, Long grpId) {
        Group group = groupRepository.findById(grpId).orElseThrow(() -> new NotFoundException(" Group with ID " + grpId + " is not found !!!"));
        Student student = studentRepository.findById(stdId).orElseThrow(() -> new NotFoundException(" Student with ID " + stdId + " is not found !!!"));
        group.getStudents().add(student);
        student.setGroup(group);
        groupRepository.save(group);
        studentRepository.save(student);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Student with ID %s is successfully assigned to Group with ID %s ",student.getId(),group.getId())
        );
    }

    @Override
    public SimpleResponse updateStudent(Long stdId, Long grpId, UniRegistrRequest uniRegistrRequest) {
        Group group = groupRepository.findById(grpId).orElseThrow(() -> new NotFoundException(" Group with ID " + grpId + " is not found !!!"));
        Student student = studentRepository.findById(stdId).orElseThrow(() -> new NotFoundException(" Student with ID " + stdId + " is not found !!!"));
        student.setGroup(group);
        student.setFirstName(uniRegistrRequest.firstName());
        student.setLastName(uniRegistrRequest.lastName());
        student.setPhoneNumber(uniRegistrRequest.phoneNumber());
        student.setEmail(uniRegistrRequest.email());
        student.setPassword(passwordEncoder.encode(uniRegistrRequest.password()));
        student.setStudyFormat(uniRegistrRequest.studyFormat());
        student.setBlocked(student.isBlocked());
        student.setUpdatedAt(ZonedDateTime.now());
        studentRepository.save(student);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Student with ID %s is successfully updated",student.getId())
        );
    }

    @Override
    public SimpleResponse blockStudent(boolean word, Long stdId) {
        Student student = studentRepository.findById(stdId).orElseThrow(
                () -> new NotFoundException (
                        "Student with id: " + stdId + " not found"
                )
        );
        student.setBlocked(word);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Student with id:%s successfully blocked",stdId)
        );
    }

    @Override
    public List<StudentResponse> sortStudyFormat(String studyFormat) {
        if (studyFormat.equalsIgnoreCase("online")){
            return studentRepository.getOnlineFormat();
        }
        else if (studyFormat.equalsIgnoreCase("offline")){
            return studentRepository.getOfflineFormat();
        }
        else {
            throw new BadCredentialException("Input correct request !!!");
        }
    }

    @Override
    public SimpleResponse deleteStudent(Long stdId, Long grpId) {
        Group group = groupRepository.findById(grpId).orElseThrow(() -> new NotFoundException(" Group with ID " + grpId + " is not found !!!"));
        Student student = studentRepository.findById(stdId).orElseThrow(() -> new NotFoundException(" Student with ID " + stdId + " is not found !!!"));
        student.setGroup(group);
        studentRepository.delete(student);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Student with ID %s is successfully deleted",student.getId())
        );
    }
}
