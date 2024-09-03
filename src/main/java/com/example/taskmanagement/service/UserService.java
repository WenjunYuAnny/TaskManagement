package com.example.taskmanagement.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanagement.model.User;
import com.example.taskmanagement.model.UserType;
import com.example.taskmanagement.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserById(String userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	boolean isManager(String userId) {
		User user = getUserById(userId);
		if (user != null) {
			if (user.getType().equals(UserType.MANAGER)) {
				return true;
			}
		}
		return false;
	}
	
	

}
