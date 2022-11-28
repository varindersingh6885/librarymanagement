package com.nagarro.training.springmvc.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.training.springmvc.entity.Author;
import com.nagarro.training.springmvc.entity.Book;
import com.nagarro.training.springmvc.service.AuthorService;
import com.nagarro.training.springmvc.service.BookService;

@Controller
//@RequestMapping("/library.management/")
@RequestMapping("/")
public class BookController {
	
	@Autowired
	BookService bookService;
	@Autowired
	AuthorService authorService;
	
//	private static RestTemplate restTemplate = new RestTemplate();
	
	//Route -> /library.management/books
	//Method -> Get
	//Function -> Returns List of all books
	@GetMapping("/books")
	public ModelAndView displayAllBooks() {
		ModelAndView mv = new ModelAndView("displayBooks");
		
		try {
			// Fetching all books from the REST API
//			Book[] books = restTemplate.getForObject("http://localhost:8080/library.management/api/v1/books", Book[].class);
			List<Book> booksList = bookService.getAllBooks();
			Book[] books = new Book[booksList.size()];
			for(int i = 0; i < booksList.size(); i++) {
				books[i] = booksList.get(i);
			}
			mv.addObject("books",books); // add books to the model
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("errorMsg", "Server Down! Try after some time"); // add error message if fetching books fail
		}
		
		return mv;
	}
	
	//Route -> /library.management/books/add
	//Method -> Get
	//Function -> Returns View to Add new Book
	@GetMapping("/books/add")
	public ModelAndView addBookForm() {
		ModelAndView mv = new ModelAndView("addBook");

		// creating date object for the new book to be added
		DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
		Date date = new Date();
		String currDate = dateFormat.format(date);
		mv.addObject("date",currDate); // add date to model
		try {
			// fetching all the authors from the REST API
//			Author[] authors = restTemplate.getForObject("http://localhost:8080/library.management/api/v1/authors", Author[].class);
			
			List<Author> authorsList = authorService.getAllAuthors();
			Author[] authors = new Author[authorsList.size()];
			for(int i = 0; i < authorsList.size(); i++) {
				authors[i] = authorsList.get(i);
			}
			
			mv.addObject("authors", authors); // add authors to model
		} catch (Exception e) {
			mv.addObject("errorMsg", "Server Down! Try after some time"); // add error message if fetching authors fail
		}
		
		return mv;
	}
	
	//Route -> /library.management/books/add
	//Method -> Post
	//Function -> Adds the book details to the database
	@PostMapping("/books/add")
	public ModelAndView addBook(@RequestParam("bookCode") String bookCode,
			                    @RequestParam("bookName") String bookName,
			                    @RequestParam("author") int authorId,
			                    HttpServletRequest request
							   ) {
		ModelAndView mv = new ModelAndView();
		
		// validate if the user is logged in
		String username = (String) request.getSession().getAttribute("username");
		
		if(username == null) {
			mv.setViewName("redirect:/library.management/login");
			return mv;
		}
		
		mv.setViewName("addBook");
		
		// create parameters for the request url to get author
		HashMap<String, Integer> params = new HashMap<>();
		params.put("id", authorId);
		
		// fetching author with given id
//		Author author = restTemplate.getForObject("http://localhost:8080/library.management/api/v1/authors/{id}", Author.class, params);
		Author author = authorService.getAuthorById(authorId);
		Date d = new Date();
		
		// create book object to send in request to be saved
		// set all data members
		Book book = new Book();
		book.setBookCode(bookCode);
		book.setBookName(bookName);
		book.setAuthor(author);
		book.setDateCreated(d);
		
		DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
		Date date = new Date();
		String currDate = dateFormat.format(date); // to display in the book add form
		
//		Author[] authors = restTemplate.getForObject("http://localhost:8080/library.management/api/v1/authors", Author[].class);
		List<Author> authorsList = authorService.getAllAuthors();
		Author[] authors = new Author[authorsList.size()];
		for(int i = 0; i < authorsList.size(); i++) {
			authors[i] = authorsList.get(i);
		}
		
		mv.addObject("authors",authors);
		mv.addObject("date",currDate);
		mv.addObject("book",book);

		try {
			// sending post request to save the book details
//			restTemplate.postForObject("http://localhost:8080/library.management/api/v1/books", book, Book.class);
			
			bookService.addBook(book);
			
			mv.addObject("successMsg","Book Details Added Successfully!");
		} catch (HttpClientErrorException e) {
			int statusCode = e.getRawStatusCode();
			if(statusCode == 409) {
				mv.addObject("errorMsg","Book Code exists already! Try changing it");
			}
		}
		
		return mv;
	}
	
	//Route -> /library.management/books/edit/{id}
	//Method -> Get
	//Function -> Returs a view to edit book details with given id
	@GetMapping("/books/edit/{id}")
	public ModelAndView editBook(@PathVariable("id") String bookCode) {
		ModelAndView mv = new ModelAndView("editBookForm");
		
		HashMap<String, String> params = new HashMap<>();
		params.put("id", bookCode);
		
//		Book book = restTemplate.getForObject("http://localhost:8080/library.management/api/v1/books/{id}", Book.class, params);
		Book book = bookService.getBookById(bookCode);
		
//		Author[] authors = restTemplate.getForObject("http://localhost:8080/library.management/api/v1/authors", Author[].class);
		List<Author> authorsList = authorService.getAllAuthors();
		Author[] authors = new Author[authorsList.size()];
		for(int i = 0; i < authorsList.size(); i++) {
			authors[i] = authorsList.get(i);
		}
		
		mv.addObject("book",book);
		mv.addObject("authors", authors);
		
		return mv;
	}
	
	//Route -> /library.management/books/edit/{id}
	//Method -> Post
	//Function -> Updates the book details in the Database
	@PostMapping("/books/edit/{id}")
	public ModelAndView updateBook(@RequestParam("bookCode") String bookCode,
						           @RequestParam("bookName") String bookName,
						           @RequestParam("author") int authorId,
						           @RequestParam("date") Date date,
						           HttpServletRequest request
			                      ) {
		ModelAndView mv = new ModelAndView();
		
		
		// validate if the user is logged in
		String username = (String) request.getSession().getAttribute("username");
		
		if(username == null) {
			mv.setViewName("redirect:/library.management/login");
			return mv;
		}
		
		mv.setViewName("editBookForm");
		
		// create parameters for REST API of author and book
		HashMap<String, Integer> paramsForAuthor = new HashMap<>();
		paramsForAuthor.put("id", authorId);
		HashMap<String, String> paramsForBook = new HashMap<>();
		paramsForBook.put("id", bookCode);
		
		try {
//			Author author = restTemplate.getForObject("http://localhost:8080/library.management/api/v1/authors/{id}", Author.class, paramsForAuthor);
			Author author = authorService.getAuthorById(authorId);
			
			Book book = new Book();
			book.setBookCode(bookCode);
			book.setBookName(bookName);
			book.setAuthor(author);
			book.setDateCreated(date);
			
			mv.addObject("book",book);
			paramsForBook.put("id", bookCode);
			
			try {
//				restTemplate.put("http://localhost:8080/library.management/api/v1/books/{id}", book, paramsForBook);		
				bookService.updateBookById(bookCode, book);
				
				mv.addObject("successMsg", "Book Details Updated Successfully!");
			} catch (Exception e) {
				mv.addObject("errorMsg","Unable to update book details!");
			}
			
//			Author[] authors = restTemplate.getForObject("http://localhost:8080/library.management/api/v1/authors", Author[].class);
			List<Author> authorsList = authorService.getAllAuthors();
			Author[] authors = new Author[authorsList.size()];
			for(int i = 0; i < authorsList.size(); i++) {
				authors[i] = authorsList.get(i);
			}
			mv.addObject("authors",authors);

		} catch (Exception e) {
			mv.addObject("errorMsg","Server Down! Unable to connect.");
		}
		
		return mv;
	}
	
	//Route -> /library.management/books/delete/{id}
	//Method -> Get
	//Function -> Delete the book with given id
	@GetMapping("/books/delete/{id}")
	public ModelAndView deleteBook(@PathVariable("id") String bookCode, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/books");
		
		// validate if the user is logged in
		String username = (String) request.getSession().getAttribute("username");
		
		if(username == null) {
			mv.setViewName("redirect:/login");
			return mv;
		}
		
		HashMap<String, String> params = new HashMap<>();
		params.put("id", bookCode);
		
		try {
//			restTemplate.delete("http://localhost:8080/library.management/api/v1/books/{id}", params);			
			bookService.deleteBookById(bookCode);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return mv;
	}
}
