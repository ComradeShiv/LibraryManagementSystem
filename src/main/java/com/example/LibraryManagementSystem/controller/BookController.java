package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.dto.requestDTO.BookRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.exception.GenreNotFoundException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody BookRequest bookRequest) {
        try{
            return bookService.addBook(bookRequest);
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
    @DeleteMapping("/deleteBook")
    public ResponseEntity deleteBook(@RequestParam("id") int id) {
        BookResponse bookResponse = bookService.deleteBook(id);
        if(bookResponse == null) {
            return new ResponseEntity("Invalid book ID !!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(bookResponse, HttpStatus.GONE);
    }

    // give me names of all books of a particular genre
    @GetMapping("/booksWithGenre/{genre}")
    public ResponseEntity booksWithGenre(@PathVariable("genre") String genre) {
        try {
            List<String> bookList = bookService.booksWithGenre(genre);
            if(bookList.isEmpty()) {
                return new ResponseEntity("No books with " + genre + " available !!", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(bookList, HttpStatus.FOUND);
        } catch (GenreNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // give me names of all books of a particular genre & cost greater than 500 rs
    @GetMapping("/getAllBooksWithGenreAndCost/{genre}/{cost}")
    public ResponseEntity getAllBooksWithGenreAndCostGreaterThan(@PathVariable("genre") String genre, @PathVariable("cost") double cost) {
        List<BookResponse> response = bookService.getAllBooksWithGenreAndCostGreaterThan(genre, cost);
        if(response.isEmpty()) {
            return new ResponseEntity("No Such book Available", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response, HttpStatus.FOUND);
    }

    // give me all books having no of pages between 'a' and 'b'
    @GetMapping("/getAllBooksWithNoOfPagesBetween")
    public ResponseEntity booksHavingNoOfPagesBetween(@RequestParam("a") int a, @RequestParam("b") int b) {
        List<BookResponse> bookResponses = bookService.booksHavingNoOfPagesBetween(a, b);
        if(bookResponses.isEmpty()) {
            return new ResponseEntity<>("No Such Book available", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookResponses, HttpStatus.FOUND);
    }

    // give me names of all the author who writes a particular genre
}
