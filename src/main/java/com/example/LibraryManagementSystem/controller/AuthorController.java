package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody Author author) {
        String response = authorService.addAuthor(author);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    // update the email id oa an author  ---> observe lastActivity while doing this

    // give me the names of all books written by a particular author

    // give me the names of authors who have written more than 'x' number of books
}
