package com.nagarro.training.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.training.springmvc.entity.Author;


public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
