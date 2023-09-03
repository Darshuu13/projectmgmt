package com.project.management.app.controller;

import com.project.management.app.exception.ResourceNotFoundException;
import com.project.management.app.request.TaskRequest;
import com.project.management.app.response.TaskResponse;
import com.project.management.app.serviceImpl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TaskController {

    @Autowired
    TaskServiceImpl taskServiceImpl;

    @PostMapping("/task")
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest) throws ResourceNotFoundException {
        return taskServiceImpl.createTask(taskRequest);
    }

    @PutMapping("/task")
    public TaskResponse updateTask(
            @RequestBody TaskRequest taskRequest) throws ResourceNotFoundException {
        return taskServiceImpl.updateTask(taskRequest);
    }

    @DeleteMapping("/task/{taskId}")
    public Map<String, Boolean> deleteTask(@PathVariable(value = "taskId") Long taskId)
            throws ResourceNotFoundException {

        taskServiceImpl.deleteTask(taskId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
