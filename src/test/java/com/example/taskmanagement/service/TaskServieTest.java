package com.example.taskmanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.*;

public class TaskServieTest {
	@Mock
    private UserService userService;
	
	@Mock
    private UserRepository userRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void createTask_asManager_shouldReturnTask() {
        // Arrange
        String creatorId = "manager1";
        String projectId = "project1";
        String name = "New Task";
        Date dueTs = new Date();
        String desc = "Task Description";
        
        Task newTask = new Task(creatorId, projectId, name, dueTs, desc);
        newTask.setId("task1");
        
        when(userService.isManager(creatorId)).thenReturn(true);
        when(projectRepository.existsById(projectId)).thenReturn(true);
        when(taskRepository.save(any(Task.class))).thenReturn(newTask);

        // Act
        Task result = taskService.createTask(creatorId, projectId, name, dueTs, desc);

        // Assert
        assertNotNull(result);
        assertEquals("task1", result.getId());
        assertEquals(creatorId, result.getCreatorId());
        assertEquals(projectId, result.getProjectId());
        assertEquals(name, result.getName());
        assertEquals(dueTs, result.getDueTs());
        assertEquals(desc, result.getDesc());
        
        verify(taskRepository).save(argThat(task -> 
        task.getCreatorId().equals(creatorId) &&
        task.getProjectId().equals(projectId) &&
        task.getName().equals(name) &&
        task.getDueTs().equals(dueTs) &&
        task.getDesc().equals(desc)));
    }
    
    @Test
    void createTask_asNonManager_shouldThrowIllegalArgumentException() {
    	// Arrange
        String creatorId = "member1";
        String projectId = "project1";
        String name = "New Task";
        Date dueTs = new Date();
        String desc = "Task Description";
        
        when(userService.isManager(creatorId)).thenReturn(false);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            taskService.createTask(creatorId, projectId, name, dueTs, desc);
        });
        assertEquals("Manager access needed to create a task", thrown.getMessage());
    }
    
    @Test
    void createTask_projectDoesNotExist_shouldThrowIllegalArgumentException() {
    	// Arrange
        String creatorId = "member1";
        String projectId = "project1";
        String name = "New Task";
        Date dueTs = new Date();
        String desc = "Task Description";
        
        when(userService.isManager(creatorId)).thenReturn(true);
        when(projectRepository.existsById(projectId)).thenReturn(false);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            taskService.createTask(creatorId, projectId, name, dueTs, desc);
        });
        assertEquals("No such project exists", thrown.getMessage());
    }
    
    @Test
    void assignTask_asManagerAndValidIds_shouldAssignTaskAndSave() {
    	//arrange
        String creatorId = "manager1";
        String taskId = "task1";
        String assigneeId = "user1";
        
        Task task = new Task();
        task.setId(taskId);
        
        when(userService.isManager(creatorId)).thenReturn(true);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(userRepository.existsById(assigneeId)).thenReturn(true);
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        
        //act
        Task result = taskService.assignTask(creatorId, taskId, assigneeId);

        //assert
        assertNotNull(result);
        assertTrue(result.getAssigneeIds().contains(assigneeId));
    }
    
    @Test
    void assignTask_asNonManager_shouldThrowIllegalArgumentException() {
    	//arrange
        String creatorId = "manager1";
        String taskId = "task1";
        String assigneeId = "user1";
        
        when(userService.isManager(creatorId)).thenReturn(false);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            taskService.assignTask(creatorId, taskId, assigneeId);
        });
        assertEquals("Manager access needed to assign a task", thrown.getMessage());
    }
    
    @Test
    void assignTask_taskDoesNotExist_shouldThrowIllegalArgumentException() {
    	//arrange
        String creatorId = "manager1";
        String taskId = "task1";
        String assigneeId = "user1";
        
        when(userService.isManager(creatorId)).thenReturn(true);
        when(taskRepository.existsById(taskId)).thenReturn(false);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            taskService.assignTask(creatorId, taskId, assigneeId);
        });
        assertEquals("No such task exists", thrown.getMessage());
    }
    
    @Test
    void assignTask_userDoesNotExist_shouldThrowIllegalArgumentException() {
    	//arrange
        String creatorId = "manager1";
        String taskId = "task1";
        String assigneeId = "user1";
        
        Task task = new Task();
        task.setId(taskId);
        
        when(userService.isManager(creatorId)).thenReturn(true);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(userRepository.existsById(assigneeId)).thenReturn(false);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            taskService.assignTask(creatorId, taskId, assigneeId);
        });
        assertEquals("No such user exists", thrown.getMessage());
    }
}
