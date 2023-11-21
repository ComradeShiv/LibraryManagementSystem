package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.responseDTO.IssuedBookResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.ReturnBookResponse;
import com.example.LibraryManagementSystem.exception.BookNotAvailableException;
import com.example.LibraryManagementSystem.exception.BookNotFoundException;
import com.example.LibraryManagementSystem.exception.BookNotIssuedException;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;
import com.example.LibraryManagementSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    // Issue a book
    @PostMapping("/issueBook/student-id/{student-id}/book-id/{book-id}")
    public ResponseEntity issuedBook(@PathVariable("student-id") int studentId, @PathVariable("book-id") int bookId) {
        try {
            IssuedBookResponse issuedBookResponse = transactionService.issuedBook(studentId, bookId);
            return new ResponseEntity(issuedBookResponse, HttpStatus.FOUND);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BookNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BookNotAvailableException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // return a book
    @DeleteMapping("/returnBook/student-id/{studentId}/book-id/{bookId}")
    public ResponseEntity returnBook(@PathVariable("studentId") int studentId, @PathVariable("bookId") int bookId) {
        try {
            ReturnBookResponse returnBookResponse = transactionService.returnBook(studentId, bookId);
            return new ResponseEntity(returnBookResponse, HttpStatus.FOUND);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BookNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BookNotIssuedException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}
