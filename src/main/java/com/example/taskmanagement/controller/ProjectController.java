package com.example.taskmanagement.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.taskmanagement.model.*;
import com.example.taskmanagement.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	@PostMapping("/create")
	public ResponseEntity<Project> createProject(
			@RequestParam String creatorId,
			@RequestParam String name,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dueTs,
			@RequestParam String desc) {
		try {
			Project project = projectService.createProject(creatorId, name, dueTs, desc);
			return new ResponseEntity<>(project, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
	}
}
