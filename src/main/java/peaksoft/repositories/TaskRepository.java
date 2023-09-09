package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.TaskRequest;
import peaksoft.dto.TaskResponse;
import peaksoft.models.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select new peaksoft.dto.TaskResponse(t.id,t.taskName,t.taskText,t.deadline,t.createdAt,t.updatedAt) from Task t where t.lesson.id=:lsnId")
    List<TaskResponse> getAllTasksOfLesson(Long lsnId);

    @Query("select new peaksoft.dto.TaskResponse(t.id,t.taskName,t.taskText,t.deadline,t.createdAt,t.updatedAt) from Task t where t.id=:taskId and t.lesson.id =:lsnId")
    TaskResponse getTaskFromLessonById(Long lsnId,Long taskId);

}