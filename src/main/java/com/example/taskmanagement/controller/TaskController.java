package com.example.taskmanagement.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/create")
	public ResponseEntity<Task> createTask(
			@RequestParam String creatorId,
			@RequestParam String projectId,
			@RequestParam String name,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dueTs,
			@RequestParam String desc) {
		try {
			Task task = taskService.createTask(creatorId, projectId, name, dueTs, desc);
			return new ResponseEntity<>(task, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
	}
	
	@PostMapping("/{taskId}/assign")
	public ResponseEntity<Task> assignTask(
			@RequestParam String creatorId,
			@PathVariable String taskId,
			@RequestParam String assigneeId) {
		try {
			Task task = taskService.assignTask(creatorId, taskId, assigneeId);
			return new ResponseEntity<>(task, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
	
}
