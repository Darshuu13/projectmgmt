package com.project.management.app.controller;

import com.project.management.app.exception.ResourceNotFoundException;
import com.project.management.app.request.ProjectRequest;
import com.project.management.app.response.ProjectResponse;
import com.project.management.app.serviceImpl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProjectController {
    @Autowired
    ProjectServiceImpl projectServiceImpl;

    @PostMapping(path = "/project",  consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ProjectResponse createProject(@ModelAttribute ProjectRequest projectRequest,BindingResult result,
                                         Model model,
                                         @RequestPart MultipartFile imageData) throws IOException {
        projectRequest.setImageData(imageData.getBytes());
        return projectServiceImpl.createProject(projectRequest);
    }

    @PutMapping(path = "/project",  consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ProjectResponse updateProject(
            @ModelAttribute ProjectRequest projectRequest,BindingResult result,
            Model model,
            @RequestPart("imageData") MultipartFile imageData) throws ResourceNotFoundException, IOException {
        projectRequest.setImageData(imageData.getBytes());
        return projectServiceImpl.updateProject(projectRequest);
    }

    @DeleteMapping("/project/{projectId}")
    public Map<String, Boolean> deleteProject(@PathVariable(value = "projectId") Long projectId)
            throws ResourceNotFoundException {

        projectServiceImpl.delete(projectId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("get/all")
    public List<ProjectResponse> getAllProjects() {
        return projectServiceImpl.getAllProjects();
    }
}

