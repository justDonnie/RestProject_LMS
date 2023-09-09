package peaksoft.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.TaskRequest;
import peaksoft.dto.TaskResponse;
import peaksoft.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Task API")
public class TaskAPI {
    private final TaskService taskService;

    @PermitAll
    @GetMapping("/getAll/{lsnId}")
    public List<TaskResponse> getAllTasksOfLesson(@PathVariable Long lsnId){
        return taskService.getAllTasks(lsnId);
    }
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PostMapping("/save/{lsnId}")
    public SimpleResponse saveTaskToLesson(@RequestBody TaskRequest taskRequest,
                                           @PathVariable Long lsnId){
        return taskService.saveTask(taskRequest,lsnId);
    }
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','STUDENT')")
    @GetMapping ("/get/{taskId}/{lsnId}")
    public TaskResponse getTaskFromLesson(@PathVariable Long taskId,
                                          @PathVariable Long lsnId){
        return taskService.getTaskById(taskId,lsnId);
    }
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @PutMapping("/update/{taskId}/{lsnId}")
    public SimpleResponse updateTaskOFLesson(@PathVariable Long taskId,
                                             @PathVariable Long lsnId,
                                             @RequestBody TaskRequest taskRequest){
        return taskService.updateTask(taskId,lsnId,taskRequest);
    }
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    @DeleteMapping("/delete/{taskId}/{lsnId}")
    public SimpleResponse deleteTaskFromLesson(@PathVariable Long taskId,
                                               @PathVariable Long lsnId){
        return taskService.deleteTask(taskId,lsnId);
    }


}
