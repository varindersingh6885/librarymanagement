package com.nagarro.training.springmvc.service;

import com.nagarro.training.springmvc.entity.User;

public interface UserService {
	User getUserByUsername(String username);
	Boolean isUserValid(User user);
}
