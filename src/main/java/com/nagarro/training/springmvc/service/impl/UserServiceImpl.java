package com.nagarro.training.springmvc.service.impl;

import org.springframework.stereotype.Service;

import com.nagarro.training.springmvc.entity.User;
import com.nagarro.training.springmvc.repository.UserRepository;
import com.nagarro.training.springmvc.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public User getUserByUsername(String username) {
		return userRepository.findById(username).orElse(null);
	}


	@Override
	public Boolean isUserValid(User user) {
		User userInDB = getUserByUsername(user.getUsername());
		if(userInDB == null) {
			System.err.println("user not found");
		} else if( userInDB.getPassword().equals(user.getPassword())) {
			return true;
		} else {
			System.err.println("Wrong password");
		}
		return false;
	}

}
