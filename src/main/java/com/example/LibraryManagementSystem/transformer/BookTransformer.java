package com.example.LibraryManagementSystem.transformer;

import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.model.Book;

public class BookTransformer {

    public static BookResponse bookToBookResponse(Book book) {
        return BookResponse.builder()
                .title(book.getTitle())
                .genre(book.getGenre())
                .authorName(book.getAuthor().getName())
                .cost(book.getCost())
                .noOfPages(book.getNoOfPages())
                .build();
    }
}
