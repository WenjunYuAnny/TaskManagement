package com.example.taskmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagement.model.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String>{

}
