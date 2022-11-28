package com.nagarro.training.springmvc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.training.librarymanagementsystem.exception.DuplicateEntryException;
import com.nagarro.training.librarymanagementsystem.exception.ResourceNotFoundException;
import com.nagarro.training.springmvc.entity.Book;
import com.nagarro.training.springmvc.repository.BookRepository;
import com.nagarro.training.springmvc.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	private BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}


	@Override
	public Book addBook(Book book) {
		String bookCode = book.getBookCode();
		Book isBookPresent = bookRepository.findById(bookCode).orElse(null);
		if(isBookPresent != null) {
			throw new DuplicateEntryException("Duplicate entry");
		}
		return bookRepository.save(book);
	}


	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}


	@Override
	public Book getBookById(String id) {
		return bookRepository.findById(id).orElseThrow(() ->
								new ResourceNotFoundException("Book not found with " + id));
	}


	@Override
	public Book updateBookById(String id, Book book) {
		Book existingBook = bookRepository.findById(id).orElseThrow(() ->
									new ResourceNotFoundException("Book not found with " + id)
								);
		existingBook.setBookName(book.getBookName());
		existingBook.setAuthor(book.getAuthor());
		return bookRepository.save(existingBook);
	}


	@Override
	public void deleteBookById(String id) {
		Book existingBook = bookRepository.findById(id).orElseThrow(() ->
									new ResourceNotFoundException("Book not found with " + id)
								);
		bookRepository.delete(existingBook);
	}

}
