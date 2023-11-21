package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.dto.requestDTO.BookRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.exception.GenreNotFoundException;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public String addBook(BookRequest bookRequest) {

        // check whether author is present or not
        Optional<Author> authorOptional = Optional.ofNullable(authorRepository.findByName(bookRequest.getAuthorName()));
        if(authorOptional.isEmpty()) {
            throw new AuthorNotFoundException("Invalid Author Name");
        }
        Book book = new Book();

        book.setTitle(bookRequest.getTitle());
        book.setNoOfPages(bookRequest.getNoOfPages());
        book.setGenre(bookRequest.getGenre());
        book.setCost(bookRequest.getCost());
        book.setIssued(false);

        Author author = authorOptional.get();
        book.setAuthor(author);
        author.getBooks().add(book);

        authorRepository.save(author); // update author & save book
        return "Book Successfully added";
    }

    public Book getBook(int id) {
        return bookRepository.getReferenceById(id);
    }

//    public List<BookResponse> getAllBooksWithGenreAndCostGreaterThan(Genre genre, double cost) {
//        List<Book> books = bookRepository.findByGenre(genre);
//
//        List<BookResponse> response = new ArrayList<>(); // list of response DTOs
//        for(Book book: books) {
//            if(book.getCost() > cost) {
//                BookResponse reqBook = new BookResponse(); // crete & adding all info about a book to its DTOs
//
//                reqBook.setTitle(book.getTitle());
//                reqBook.setNoOfPages(book.getNoOfPages());
//                reqBook.setGenre(book.getGenre());
//                reqBook.setCost(book.getCost());
//                reqBook.setAuthorName(book.getAuthor().getName());
//
//                response.add(reqBook);
//            }
//        }
//        return response;
//    }

    public List<BookResponse> getAllBooksWithGenreAndCostGreaterThan(String genre, double cost) {
        List<Book> books = bookRepository.getAllBooksWithGenreAndCostGreaterThan(genre, cost);

        List<BookResponse> bookResponses = new ArrayList<>();
        for(Book book: books) {
            BookResponse bookResponse = new BookResponse();

            bookResponse.setTitle(book.getTitle());
            bookResponse.setAuthorName(book.getAuthor().getName());
            bookResponse.setNoOfPages(book.getNoOfPages());
            bookResponse.setGenre(book.getGenre());
            bookResponse.setCost(book.getCost());

            bookResponses.add(bookResponse);
        }

        return bookResponses;
    }

    public BookResponse deleteBook(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(!optionalBook.isPresent())
            return null;

        BookResponse bookResponse = BookTransformer.bookToBookResponse(optionalBook.get());

//        BookResponse bookResponse = new BookResponse();
//
//        bookResponse.setTitle(optionalBook.get().getTitle());
//        bookResponse.setAuthorName(optionalBook.get().getAuthor().getName());
//        bookResponse.setGenre(optionalBook.get().getGenre());
//        bookResponse.setNoOfPages(optionalBook.get().getNoOfPages());
//        bookResponse.setCost(optionalBook.get().getCost());

        Book deletedBook = bookRepository.deleteById(id);

        return bookResponse;
    }

    public List<String> booksWithGenre(String genre) {
        boolean isGenrePresent = false;
        for(Genre g: Genre.values()) {
            if(g.toString().equals(genre)) {
                isGenrePresent = true;
                break;
            }
        }
        if(!isGenrePresent) {
            throw new GenreNotFoundException("No such genre found !!!");
        }

        return bookRepository.booksWithGenre(genre);

//        List<Book> bookList = bookRepository.findByGenre(Genre.valueOf(genre));
//
//        List<String> bookWithReqGenre = new ArrayList<>();
//        for(Book book: bookList) {
//            if(book.getGenre().equals(Genre.valueOf(genre))) {
//                bookWithReqGenre.add(book.getTitle());
//            }
//        }
//        return bookWithReqGenre;
    }

    public List<BookResponse> booksHavingNoOfPagesBetween(int a, int b) {
        List<Book> books = bookRepository.booksHavingNoOfPagesBetween(a, b);

        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book: books) {
            bookResponses.add(BookTransformer.bookToBookResponse(book));
        }

        return bookResponses;
    }
}
