package peaksoft.services;

import peaksoft.dto.*;

import java.util.List;

public interface StudentService {
    SimpleResponse saveStudent(UniRegistrRequest uniRegistrRequest);

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(Long stdId);

    SimpleResponse assignStudentToGroup(Long stdId, Long grpId);

    SimpleResponse updateStudent(Long stdId,Long grpId,UniRegistrRequest uniRegistrRequest);

    SimpleResponse deleteStudent(Long lsnId, Long crsId);

    SimpleResponse blockStudent(boolean word, Long stdId);


}
