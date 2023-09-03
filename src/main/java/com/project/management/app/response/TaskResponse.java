package com.project.management.app.response;

import com.project.management.app.entity.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {
    private Long taskId;
    private String name;
    private Boolean isCompleted;
    private Long projectId;

    public TaskResponse(Task task) {
        this.taskId = task.getTaskId();
        this.name = task.getName();
        this.isCompleted = task.getIsCompleted();
        this.projectId = task.getProject().getProjectId();
    }
}
