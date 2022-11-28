package com.nagarro.training.springmvc.service;

import java.util.List;

import com.nagarro.training.springmvc.entity.Book;


public interface BookService {
	Book addBook(Book book);
	List<Book> getAllBooks();
	Book getBookById(String id);
	Book updateBookById(String id, Book book);
	void deleteBookById(String id);
}
