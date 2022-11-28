package com.nagarro.training.springmvc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.training.librarymanagementsystem.exception.ResourceNotFoundException;
import com.nagarro.training.springmvc.entity.Author;
import com.nagarro.training.springmvc.repository.AuthorRepository;
import com.nagarro.training.springmvc.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	private AuthorRepository authorRepository;
	
	public AuthorServiceImpl(AuthorRepository authorRepository) {
		super();
		this.authorRepository = authorRepository;
	}

	@Override
	public Author saveAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}
	
	@Override
	public Author getAuthorById(int id) {
		return authorRepository.findById(id).orElseThrow(() ->
					new ResourceNotFoundException("Author not found with " + id));
	}

	@Override
	public Author updateAuthorById(int id, Author author) {
		// check if the author with given id exists
		Author existingAuthor = authorRepository.findById(id).orElseThrow(() ->
													new ResourceNotFoundException("Author not found with " + id));
		existingAuthor.setFirstName(author.getFirstName());
		existingAuthor.setLastName(author.getLastName());
		return authorRepository.save(existingAuthor);
	}

	@Override
	public void deleteAuthorById(int id) {
		Author existingAuthor = authorRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Author not found with " + id));
		System.out.println(existingAuthor);
		authorRepository.delete(existingAuthor);
	}

}
