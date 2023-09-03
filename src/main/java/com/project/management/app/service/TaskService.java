package com.project.management.app.service;

import com.project.management.app.exception.ResourceNotFoundException;
import com.project.management.app.request.TaskRequest;
import com.project.management.app.response.TaskResponse;

public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequest) throws ResourceNotFoundException;

    TaskResponse updateTask(TaskRequest taskRequest) throws ResourceNotFoundException;

    void deleteTask(Long taskId) throws ResourceNotFoundException;
}
