package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.InstructorResponse;
import peaksoft.dto.InstructorsFullInfo;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UniRegistrRequest;
import peaksoft.services.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
@Tag(name = "Instructor API")
public class InstructorAPI {

    private final InstructorService instructorService;

    @PermitAll
    @GetMapping
    public List<InstructorResponse> getAllInstructors(){
        return instructorService.getAllInstructors();
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping("/save")
//    public SimpleResponse saveInstructors(@RequestBody @Valid InstructorRequest instructorRequest){
//        return instructorService.saveInstructor(instructorRequest);
//    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @GetMapping("/get/{instId}")
    public InstructorResponse getInstructorById(@PathVariable Long instId){
        return instructorService.getInstructorById(instId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{instId}")
    public SimpleResponse updateInstructorById(@PathVariable Long instId,
                                               @RequestBody @Valid UniRegistrRequest uniRegistrRequest){
        return instructorService.updateInstructor(instId,uniRegistrRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/assignToComp/{instId}/{compId}")
    public SimpleResponse assignInstructorToCompany(@PathVariable Long instId,
                                                    @PathVariable Long compId){
        return instructorService.assignInstToComp(instId,compId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/assignToCourse/{instId}/{courseId}")
    public SimpleResponse assignInstructorToCourse(@PathVariable Long instId,
                                                   @PathVariable Long courseId){
        return instructorService.assignInstToCourse(instId,courseId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/full/{instId}")
    public InstructorsFullInfo instructorsFullInfo(@PathVariable Long instId){
        return instructorService.getInstructorInfo(instId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{instId}")
    public SimpleResponse deleteInstructorById(@PathVariable Long instId){
        return instructorService.deleteInstructor(instId);
    }


}
