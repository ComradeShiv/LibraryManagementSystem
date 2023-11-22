package com.example.LibraryManagementSystem.transformer;

import com.example.LibraryManagementSystem.dto.requestDTO.BookRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.IssuedBookResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.ReturnBookResponse;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.model.Transaction;
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

    public static IssuedBookResponse prepareIssuedBookResponse(Transaction transaction, Book book, Student student) {
        return IssuedBookResponse.builder()
                .transactionNumber(transaction.getTransactionNumber())
                .transactionTime(transaction.getTransactionTime())
                .transactionStatus(transaction.getTransactionStatus())
                .bookTitle(book.getTitle())
                .authorName(book.getAuthor().getName())
                .studentName(student.getName())
                .email(student.getEmail())
                .libraryCardNumber(student.getLibraryCard().getCardNo())
                .totalCost(book.getCost())
                .build();
    }

    public static ReturnBookResponse prepareReturnBookResponse(Transaction transaction, Book book, Student student) {
        return ReturnBookResponse.builder()
                .transactionNumber(transaction.getTransactionNumber())
                .transactionTime(transaction.getTransactionTime())
                .transactionStatus(transaction.getTransactionStatus())
                .bookTitle(book.getTitle())
                .authorName(book.getAuthor().getName())
                .studentName(student.getName())
                .libraryCardNumber(student.getLibraryCard().getCardNo())
                .build();
    }
}
