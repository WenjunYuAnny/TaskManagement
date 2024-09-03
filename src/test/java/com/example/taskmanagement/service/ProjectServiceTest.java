package com.example.taskmanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.taskmanagement.model.Project;
import com.example.taskmanagement.repository.ProjectRepository;

public class ProjectServiceTest {
	@Mock
    private UserService userService;
	
	@Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void createProject_asManager_shouldReturnProject() {
        // Arrange
        String creatorId = "manager1";
        String name = "New Project";
        Date dueTs = new Date();
        String desc = "Project Description";

        when(userService.isManager(creatorId)).thenReturn(true);

        Project newProject = new Project();
        newProject.setId("project1");
        newProject.setCreatorId(creatorId);
        newProject.setName(name);
        newProject.setDueTs(dueTs);
        newProject.setDesc(desc);

        when(projectRepository.save(any(Project.class))).thenReturn(newProject);

        // Act
        Project result = projectService.createProject(creatorId, name, dueTs, desc);

        // Assert
        assertNotNull(result);
        assertEquals("project1", result.getId());
        assertEquals(creatorId, result.getCreatorId());
        assertEquals(name, result.getName());
        assertEquals(dueTs, result.getDueTs());
        assertEquals(desc, result.getDesc());
        
        verify(projectRepository).save(argThat(project -> 
        project.getCreatorId().equals(creatorId) &&
        project.getName().equals(name) &&
        project.getDueTs().equals(dueTs) &&
        project.getDesc().equals(desc)));
    }
}
