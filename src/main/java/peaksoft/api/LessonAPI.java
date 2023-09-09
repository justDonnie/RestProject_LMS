package peaksoft.api;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.LessonRequest;
import peaksoft.dto.LessonResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.services.LessonService;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
@Tag(name = "Lesson API")
public class LessonAPI {

    private final LessonService lessonService;

    @PermitAll
    @GetMapping("/getAll/{crsId}")
    public List<LessonResponse> getAllLessons(@PathVariable Long crsId){
        return lessonService.getAllLessons(crsId);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping
    public SimpleResponse saveLessonsToCourses(@RequestBody LessonRequest lessonRequest,
                                               @RequestParam Long crsId){
        return lessonService.saveLesson(lessonRequest,crsId);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @GetMapping("/get/{lessonId}")
    public LessonResponse getLessonById(@PathVariable Long lessonId){
        return lessonService.getLessonById(lessonId);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PutMapping("/update/{crsId}/{lessonId}")
    public SimpleResponse updateLessons(@PathVariable Long crsId,
                                        @PathVariable Long lessonId,
                                        @RequestBody LessonRequest lessonRequest){
        return lessonService.updateLesson(crsId,lessonId,lessonRequest);
    }

    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @DeleteMapping("/delete/{lsnId}/{crsId}")
    public SimpleResponse deleteLessons(@PathVariable Long lsnId,
                                        @PathVariable Long crsId){
        return lessonService.deleteLesson(lsnId, crsId);
    }










}
