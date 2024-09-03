package com.example.taskmanagement.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanagement.model.Project;
import com.example.taskmanagement.repository.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;
    
    //only manager can create a project
    public Project createProject(String creatorId, String name, Date dueTs, String desc) {
    	if (!userService.isManager(creatorId)) {
    		throw new IllegalArgumentException("Manager access needed to create a project");
    	}
    	Project project = new Project(creatorId, name, dueTs, desc);
    	return projectRepository.save(project);
    }
}
