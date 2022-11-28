package com.nagarro.training.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.training.springmvc.entity.Book;


public interface BookRepository extends JpaRepository<Book, String> {
}
