package com.nagarro.training.springmvc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.training.springmvc.entity.Author;
import com.nagarro.training.springmvc.service.AuthorService;


@RestController()
//@RequestMapping("/library.management/api/v1/authors")
@RequestMapping("/api/v1/authors")

public class AuthorController {
	private AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		super();
		this.authorService = authorService;
	}
	
	// author save REST API
	@PostMapping()
	public ResponseEntity<Author> saveAuthor(@RequestBody Author author){
		return new ResponseEntity<Author>(authorService.saveAuthor(author), HttpStatus.CREATED);
	}
	
	// get all authors REST API
	@GetMapping()
	public ResponseEntity<List<Author>> getAllAuthors(){
		return new ResponseEntity<List<Author>>(authorService.getAllAuthors(), HttpStatus.OK);
	}
	
	// get author by id REST API
	@GetMapping("{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable("id") int authorId){
		return new ResponseEntity<Author>(authorService.getAuthorById(authorId), HttpStatus.OK);
	}
	
	// update author REST API
	@PutMapping("{id}")
	public ResponseEntity<Author> updateAuthorById(@PathVariable("id") int authorId,@RequestBody Author author){
		return new ResponseEntity<Author>(authorService.updateAuthorById(authorId, author), HttpStatus.OK);
	}
	
	// delete author REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteAuthorById(@PathVariable("id") int id) {
		authorService.deleteAuthorById(id);
		return new ResponseEntity<String>("Author deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public String test() {
		return "working fine!";
	}
	
}
