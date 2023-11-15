package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody Book book) {
        try{
            return bookService.addBook(book);
        } catch(AuthorNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/get")
    public ResponseEntity getBook(@RequestParam("id") int id) {
        Book response = bookService.getBook(id);
        return new ResponseEntity(response, HttpStatus.FOUND);
    }

    // delete a book

    // give me names of all books of a particular genre

    // give me names of all books of a particular genre & cost greater than 500 rs

    // give me all books having no of pages between 'a' and 'b'

    // give me names of all the author who writes a particular genre
}
