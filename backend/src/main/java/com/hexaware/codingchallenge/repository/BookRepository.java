package com.hexaware.codingchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.codingchallenge.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
