package com.project.management.app.response;

import com.project.management.app.entity.Project;
import com.project.management.app.entity.Task;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectResponse {
    private Long projectId;
    private String title;
    private String description;
    private byte[] imageData;
    private Date createdAt;
    private Date updatedAt;
    private List<Task> taskIdList;

    public ProjectResponse(Project project) {
        this.projectId = project.getProjectId();
        this.title = project.getTitle();
        this.description = project.getDescription();
        this.imageData = project.getImageData();
        this.createdAt = project.getCreatedAt();
        this.updatedAt = project.getUpdatedAt();
        this.taskIdList = project.getTasks();
    }
}
