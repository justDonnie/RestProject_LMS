package peaksoft.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.TaskRequest;
import peaksoft.dto.TaskResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Lesson;
import peaksoft.models.Task;
import peaksoft.repositories.LessonRepository;
import peaksoft.repositories.TaskRepository;
import peaksoft.services.TaskService;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;


    @Override
    public SimpleResponse saveTask(TaskRequest taskRequest, Long lsnId) {
        Lesson lesson = lessonRepository.findById(lsnId)
                .orElseThrow(() -> new NotFoundException(" Lesson with ID " + lsnId + " is not found!!!"));
        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
        task.setCreatedAt(ZonedDateTime.now());
        task.setLesson(lesson);
        lesson.getTasks().add(task);
        taskRepository.save(task);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Task with ID %s is successfully saved !!!",task.getId())
        );
    }

    @Override
    public List<TaskResponse> getAllTasks(Long lsnId) {
        return taskRepository.getAllTasksOfLesson(lsnId);
    }

    @Override
    public TaskResponse getTaskById(Long taskId, Long lsnId) {
        return taskRepository.getTaskFromLessonById(taskId,lsnId);
    }

    @Override
    public SimpleResponse updateTask(Long taskId, Long lsnId, TaskRequest taskRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task with ID " + taskId + " is not found!!"));
        Lesson lesson = lessonRepository.findById(lsnId).orElseThrow(() -> new NotFoundException(" Lesson with ID" + lsnId + " is not found!!!"));
        task.setLesson(lesson);
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
        task.setUpdatedAt(ZonedDateTime.now());
        taskRepository.save(task);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Task with ID %s is successfully updated!!!",taskId));
    }

    @Override
    public SimpleResponse deleteTask(Long taskId, Long lsnId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task with ID " + taskId + " is not found!!"));
        Lesson lesson = lessonRepository.findById(lsnId).orElseThrow(() -> new NotFoundException(" Lesson with ID" + lsnId + " is not found!!!"));
        task.setLesson(null);
        taskRepository.delete(task);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Task with ID %s is successfully deleted !!!",taskId));
    }
}
