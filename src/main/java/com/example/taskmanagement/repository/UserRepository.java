package com.example.taskmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanagement.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
}
