package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StudentResponse;
import peaksoft.dto.UniRegistrRequest;
import peaksoft.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "Student API")
public class StudentAPI {
    private final StudentService studentService;

    @PermitAll
    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudents();
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
//    @PostMapping
//    public SimpleResponse saveStudent( @RequestBody @Valid UniRegistrRequest uniRegistrRequest){
//        return studentService.saveStudent(uniRegistrRequest);
//    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/get/{studentId}")
    public StudentResponse getStudentById(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping("/assign/{stdId}/{grpId}")
    public SimpleResponse assignStudentToGroup(@PathVariable Long stdId,
                                               @PathVariable Long grpId){
        return studentService.assignStudentToGroup(stdId,grpId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("/update/{stdId}/{grpId}")
    public SimpleResponse updateStudentFromGroup(@PathVariable Long stdId,
                                                  @PathVariable Long grpId,
                                                @RequestBody @Valid UniRegistrRequest uniRegistrRequest){
        return studentService.updateStudent(stdId,grpId ,uniRegistrRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PostMapping("/block/{stdId}/{word}")
    public SimpleResponse blockStudent(@PathVariable boolean word,
                                       @PathVariable Long stdId){
        return studentService.blockStudent(word,stdId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/sortStudyFormat/{word}")
    public List<StudentResponse> sortStudyFormat(@PathVariable String word){
        return studentService.sortStudyFormat(word);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @DeleteMapping("/delete/{stdId}/{grpId}")
    public SimpleResponse deleteStudentFromGroup(@PathVariable Long stdId,
                                                  @PathVariable Long grpId){
        return studentService.deleteStudent(stdId,grpId);
    }


}
