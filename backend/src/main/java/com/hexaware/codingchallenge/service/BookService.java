package com.hexaware.codingchallenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.codingchallenge.model.Book;
import com.hexaware.codingchallenge.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	public Book getBookByIsbn(String isbn) {
	    return bookRepository.findById(isbn).orElse(null);
	}
	
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}
	
	public boolean deleteBook(String isbn) {
        if (bookRepository.existsById(isbn)) {
            bookRepository.deleteById(isbn);
            return true;
        }
        return false;
    }
	
	public Book updateBook(String isbn, Book updatedBook) {
        Book existingBook = getBookByIsbn(isbn);
        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPublicationYear(updatedBook.getPublicationYear());
            return bookRepository.save(existingBook);
        }
        return null;
    }
	
}
