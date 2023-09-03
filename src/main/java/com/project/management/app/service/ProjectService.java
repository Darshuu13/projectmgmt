package com.project.management.app.service;

import com.project.management.app.exception.ResourceNotFoundException;
import com.project.management.app.request.ProjectRequest;
import com.project.management.app.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse createProject(ProjectRequest projectRequest);

    ProjectResponse updateProject(ProjectRequest projectRequest) throws ResourceNotFoundException;

    void delete(Long projectId) throws ResourceNotFoundException;

    List<ProjectResponse> getAllProjects();
}
