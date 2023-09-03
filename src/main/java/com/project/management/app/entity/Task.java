package com.project.management.app.entity;

import com.project.management.app.request.TaskRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long taskId;
    @Column
    private String name;
    @Column
    private Boolean isCompleted;
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;

    public Task(Task task) {
        this.taskId = task.getTaskId();
        this.name = task.getName();
        this.isCompleted = task.getIsCompleted();
        this.project = task.getProject();
    }

    public Task(TaskRequest taskRequest) {
        this.taskId = taskRequest.getTaskId();
        this.name = taskRequest.getName();
        this.isCompleted = taskRequest.getIsCompleted();
        //this.project = taskRequest.getProject();
    }
}
