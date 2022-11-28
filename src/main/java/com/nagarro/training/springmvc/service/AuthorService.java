package com.nagarro.training.springmvc.service;

import java.util.List;

import com.nagarro.training.springmvc.entity.Author;


public interface AuthorService {
	Author saveAuthor(Author author);
	List<Author> getAllAuthors();
	Author getAuthorById(int id);
	Author updateAuthorById(int id, Author author);
	void deleteAuthorById(int id);
}
