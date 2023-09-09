package peaksoft.services;

import peaksoft.dto.InstructorResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UniRegistrRequest;

import java.util.List;

public interface InstructorService {

    SimpleResponse saveInstructor(UniRegistrRequest uniRegistrRequest);

    List<InstructorResponse> getAllInstructors();

    InstructorResponse getInstructorById(Long instId);

    SimpleResponse updateInstructor(Long instId, UniRegistrRequest uniRegistrRequest);

    SimpleResponse deleteInstructor(Long instId);

    SimpleResponse assignInstToComp(Long instId,Long compId);

    SimpleResponse assignInstToCourse(Long instId,Long courseId);

}
