package com.example.taskmanagement.service;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.*;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    //only manager can create a task
    public Task createTask(String creatorId, String projectId, String name, Date dueTs, String desc) {
    	if (!userService.isManager(creatorId)) {
    		throw new IllegalArgumentException("Manager access needed to create a task");
    	}
    	
    	if (!projectRepository.existsById(projectId)) {
    		throw new IllegalArgumentException("No such project exists");
    	}
    	
    	Task task = new Task(creatorId, projectId, name, dueTs, desc);
    	return taskRepository.save(task);
    }
    
    //only manager can create a task
    public Task assignTask(String creatorId, String taskId, String assigneeId) {
    	if (!userService.isManager(creatorId)) {
    		throw new IllegalArgumentException("Manager access needed to assign a task");
    	}
    	
    	Task task = taskRepository.findById(taskId).orElse(null);
    	if (task == null) {
    		throw new IllegalArgumentException("No such task exists");
    	}
    	
    	if (!userRepository.existsById(assigneeId)) {
    		throw new IllegalArgumentException("No such user exists");
    	}
    	
    	task.getAssigneeIds().add(assigneeId);
    	return taskRepository.save(task);
    }

}
