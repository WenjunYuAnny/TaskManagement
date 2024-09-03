package com.example.taskmanagement.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagement.model.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String>{

}
