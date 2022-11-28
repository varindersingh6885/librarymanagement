package com.nagarro.training.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.training.springmvc.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
