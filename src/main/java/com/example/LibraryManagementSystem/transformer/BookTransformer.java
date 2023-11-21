package com.example.LibraryManagementSystem.transformer;

import com.example.LibraryManagementSystem.dto.requestDTO.BookRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

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

    // remember author not set yet --> not possible here
    public static Book bookRequestToBook(BookRequest bookRequest) {
        return Book.builder()
                .title(bookRequest.getTitle())
                .noOfPages(bookRequest.getNoOfPages())
                .genre(bookRequest.getGenre())
                .cost(bookRequest.getCost())
                .isIssued(false)
                .build();
    }
}
