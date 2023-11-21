package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.requestDTO.AuthorRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.exception.EmailAlreadyExistException;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody AuthorRequest authorRequest) {
        String response = authorService.addAuthor(authorRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    // update the email id of an author  ---> observe lastActivity while doing this
    @PutMapping("/updateEmail")
    public ResponseEntity updateEmail(@RequestParam("id") int id, @RequestParam("email") String email) {
        try {
            AuthorResponse authorResponse = authorService.updateEmail(id, email);
            return new ResponseEntity(authorResponse, HttpStatus.FOUND);
        } catch (EmailAlreadyExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (AuthorNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // give me the names of all books written by a particular author
    @GetMapping("/getBooksOfAnAuthor/{id}")
    public ResponseEntity getAllBooksByAnAuthor(@PathVariable("id") int id) {
        try {
            List<BookResponse> bookResponses = authorService.getAllBooksByAnAuthor(id);
            if(bookResponses.isEmpty()) {
                return new ResponseEntity("No books written by the Author", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity(bookResponses, HttpStatus.FOUND);
        } catch (AuthorNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // give me the name of authors who have written more than 'x' number of books
    @GetMapping("/AuthorsWrittenBooksGreaterThan")
    public ResponseEntity listOfAuthorsWrittenBooksGreaterThan(@RequestParam("books") int x) {
        List<String> authorNames = authorService.listOfAuthorsWrittenBooksGreaterThan(x);
        if(authorNames.isEmpty()) {
            return new ResponseEntity("No such Author with greater than " + x + " written books", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(authorNames, HttpStatus.FOUND);
    }
}
