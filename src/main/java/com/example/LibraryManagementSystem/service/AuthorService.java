package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.requestDTO.AuthorRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.exception.AuthorNotFoundException;
import com.example.LibraryManagementSystem.exception.EmailAlreadyExistException;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.transformer.AuthorTransformer;
import com.example.LibraryManagementSystem.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public AuthorResponse updateEmail(int id, String email) {
        Optional<Author> authorByEmail = authorRepository.findByEmailId(email);
        if(authorByEmail.isPresent()) {
            throw new EmailAlreadyExistException("Email already registered");
        }
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(!optionalAuthor.isPresent()) {
            throw new AuthorNotFoundException("Invalid author ID !!");
        }

        optionalAuthor.get().setEmailId(email);
        authorRepository.save(optionalAuthor.get());
        return AuthorTransformer.authorToAuthorResponse(optionalAuthor.get());
    }

    public String addAuthor(AuthorRequest authorRequest) {

        Author author = new Author();

        author.setName(authorRequest.getName());
        author.setAge(authorRequest.getAge());
        author.setEmailId(authorRequest.getEmail());

        Author author1 = authorRepository.save(author);
        return "Author successfully added !!!";
    }


    public List<BookResponse> getAllBooksByAnAuthor(int id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(!optionalAuthor.isPresent()) {
            throw new AuthorNotFoundException("Invalid Author ID");
        }

        List<Book> books = optionalAuthor.get().getBooks();
        List<BookResponse> bookResponses = new ArrayList<>();
        for(Book book: books) {
            bookResponses.add(BookTransformer.bookToBookResponse(book));
        }

        return bookResponses;
    }

    public List<String> listOfAuthorsWrittenBooksGreaterThan(int x) {
        return authorRepository.listOfAuthorsWrittenBooksGreaterThan(x);
    }
}
