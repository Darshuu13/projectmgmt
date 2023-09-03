package com.project.management.app.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {
    private Long taskId;
    private String name;
    private Boolean isCompleted;
    private Long projectId;
}
