package com.project.management.app.serviceImpl;

import com.project.management.app.constants.ProjectConstants;
import com.project.management.app.entity.Project;
import com.project.management.app.entity.Task;
import com.project.management.app.exception.ResourceNotFoundException;
import com.project.management.app.repository.ProjectRepository;
import com.project.management.app.repository.TaskRepository;
import com.project.management.app.request.TaskRequest;
import com.project.management.app.response.TaskResponse;
import com.project.management.app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;

    public TaskResponse createTask(TaskRequest taskRequest) throws ResourceNotFoundException {
        Project project = projectRepository.findById(taskRequest.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException(ProjectConstants.PROJECT_NOT_FOUND + taskRequest.getProjectId()));
        Task task = new Task(taskRequest);
        task.setProject(project);
        return new TaskResponse(taskRepository.save(task));
    }

    public TaskResponse updateTask(TaskRequest taskRequest) throws ResourceNotFoundException {
        Task taskOld = taskRepository.findById(taskRequest.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException(ProjectConstants.TASK_NOT_FOUND + taskRequest.getTaskId()));
        Project project = projectRepository.findById(taskRequest.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException(ProjectConstants.PROJECT_NOT_FOUND + taskRequest.getProjectId()));
        if (taskOld.getProject().getProjectId() != project.getProjectId())
            throw new ResourceNotFoundException(taskRequest.getTaskId() + ProjectConstants.TASK_NOT_ASSOCITED_WITH_PROJECT + project.getProjectId());
        Task updatedTask = new Task(taskRequest);
        updatedTask.setProject(project);
        return new TaskResponse(taskRepository.save(updatedTask));
    }

    @Override
    public void deleteTask(Long taskId) throws ResourceNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(ProjectConstants.TASK_NOT_FOUND + taskId));
        taskRepository.delete(task);
    }
}