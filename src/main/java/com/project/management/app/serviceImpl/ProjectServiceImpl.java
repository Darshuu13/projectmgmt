package com.project.management.app.serviceImpl;

import com.project.management.app.constants.ProjectConstants;
import com.project.management.app.entity.Project;
import com.project.management.app.exception.ResourceNotFoundException;
import com.project.management.app.repository.ProjectRepository;
import com.project.management.app.repository.TaskRepository;
import com.project.management.app.request.ProjectRequest;
import com.project.management.app.response.ProjectResponse;
import com.project.management.app.service.ProjectService;
import com.project.management.app.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TaskRepository taskRepository;

    public ProjectResponse createProject(ProjectRequest projectRequest) {
        Project project = new Project(projectRequest);
        project.setImageData(ImageUtil.compressImage(projectRequest.getImageData()));
        return new ProjectResponse(projectRepository.save(project));
    }

    public ProjectResponse updateProject(ProjectRequest projectRequest) throws ResourceNotFoundException {
        projectRepository.findById(projectRequest.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException(ProjectConstants.PROJECT_NOT_FOUND + projectRequest.getProjectId()));
        Project project = new Project(projectRequest);
        project.setImageData(ImageUtil.compressImage(projectRequest.getImageData()));
        return new ProjectResponse(projectRepository.save(project));
    }

    public void delete(Long projectId) throws ResourceNotFoundException {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(ProjectConstants.PROJECT_NOT_FOUND + projectId));
        projectRepository.delete(project);
    }

    public List<ProjectResponse> getAllProjects() {
        List<ProjectResponse> projectList = new ArrayList<>();
        projectRepository.findAll().stream().forEach(
                project ->
                        projectList.add(new ProjectResponse(project))
        );
        return projectList;
    }
}


