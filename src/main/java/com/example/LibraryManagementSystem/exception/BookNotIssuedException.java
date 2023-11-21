package com.example.LibraryManagementSystem.exception;

import com.example.LibraryManagementSystem.model.Book;

public class BookNotIssuedException extends RuntimeException {

    public BookNotIssuedException(String message) {
        super(message);
    }
}
