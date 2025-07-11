package com.hexaware.codingchallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.codingchallenge.model.Book;
import com.hexaware.codingchallenge.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/{isbn}")
	public ResponseEntity<?> getBookByISBN(@PathVariable String isbn){
		Book existingBook = bookService.getBookByIsbn(isbn);
		if (existingBook != null) {
	        return ResponseEntity.ok(existingBook);
	    } else {
	        return ResponseEntity.status(404).body("Book not found!");
	    }
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllBooks(){
		List<Book> books = bookService.getAllBooks();
	    return ResponseEntity.ok().body(books);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> addBook(@RequestBody Book book){
		Book newBook = bookService.addBook(book);
	    return ResponseEntity.status(201).body(newBook);
	}
	
	@DeleteMapping("/{isbn}")
	public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
	    Book book = bookService.getBookByIsbn(isbn);
	    if (book != null) {
	        bookService.deleteBook(isbn);
	        return ResponseEntity.ok("Book deleted successfully.");
	    } else {
	        return ResponseEntity.status(404).body("Book not found!");
	    }
	}
	
	@PutMapping("/{isbn}")
	public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody Book updatedBook) {
	    Book updated = bookService.updateBook(isbn, updatedBook);
	    if (updated != null) {
	        return ResponseEntity.ok("Book updated successfully.");
	    } else {
	        return ResponseEntity.status(404).body("Book not found!");
	    }
	}

}
