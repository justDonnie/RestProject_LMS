package peaksoft.services;


import peaksoft.dto.SimpleResponse;
import peaksoft.dto.TaskRequest;
import peaksoft.dto.TaskResponse;

import java.util.List;

public interface TaskService {

    SimpleResponse saveTask(TaskRequest taskRequest, Long lsnId);

    List<TaskResponse> getAllTasks(Long lsnId);

    TaskResponse getTaskById(Long taskId,Long lsnId);

    SimpleResponse updateTask(Long taskId,Long lsnId, TaskRequest taskRequest);

    SimpleResponse deleteTask(Long taskId,Long lsnId);

}
