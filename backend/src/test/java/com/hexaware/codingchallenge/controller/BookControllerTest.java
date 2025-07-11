package com.hexaware.codingchallenge.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.hexaware.codingchallenge.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class BookControllerTest {

    @Autowired
    private BookController bookController;

    @Test
    void testAddBook() {
        Book book = new Book("Atomic Habits", "James Clear", "in12345", 2019);
        ResponseEntity<?> response = bookController.addBook(book);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        Book saved = (Book) response.getBody();
        assertEquals(book.getIsbn(), saved.getIsbn());
        assertEquals(book.getTitle(), saved.getTitle());
        assertEquals(book.getAuthor(), saved.getAuthor());
        assertEquals(book.getPublicationYear(), saved.getPublicationYear());
    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book("Book One", "Author A", "in10001", 2020);
        Book book2 = new Book("Book Two", "Author B", "in10002", 2021);
        bookController.addBook(book1);
        bookController.addBook(book2);

        ResponseEntity<?> response = bookController.getAllBooks();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>) response.getBody();
        assertNotNull(books);
        assertTrue(books.size() >= 2);
    }

    @Test
    void testGetBookByISBN_Found() {
        Book book = new Book("Find Me", "Author X", "in20001", 2022);
        bookController.addBook(book);

        ResponseEntity<?> response = bookController.getBookByISBN("in20001");
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Book found = (Book) response.getBody();
        assertEquals("Find Me", found.getTitle());
    }

    @Test
    void testGetBookByISBN_NotFound() {
        ResponseEntity<?> response = bookController.getBookByISBN("in00000");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Book not found!", response.getBody());
    }

    @Test
    void testUpdateBook_Found() {
        Book original = new Book("Old Title", "Old Author", "in30001", 2015);
        bookController.addBook(original);

        Book updated = new Book("New Title", "New Author", "in30001", 2025);
        ResponseEntity<?> response = bookController.updateBook("in30001", updated);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book updated successfully.", response.getBody());
    }

    @Test
    void testUpdateBook_NotFound() {
        Book updated = new Book("Doesn't Exist", "Nobody", "in99999", 2025);
        ResponseEntity<?> response = bookController.updateBook("in99999", updated);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Book not found!", response.getBody());
    }

    @Test
    void testDeleteBook_Found() {
        Book book = new Book("To Be Deleted", "Delete Me", "in40001", 2018);
        bookController.addBook(book);

        ResponseEntity<String> response = bookController.deleteBook("in40001");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book deleted successfully.", response.getBody());
    }

    @Test
    void testDeleteBook_NotFound() {
        ResponseEntity<String> response = bookController.deleteBook("in00000");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Book not found!", response.getBody());
    }
}
