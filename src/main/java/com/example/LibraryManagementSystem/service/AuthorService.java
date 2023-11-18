package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.requestDTO.AuthorRequest;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(AuthorRequest authorRequest) {

        Author author = new Author();

        author.setName(authorRequest.getName());
        author.setAge(authorRequest.getAge());
        author.setEmailId(authorRequest.getEmail());

        Author author1 = authorRepository.save(author);
        return "Author successfully added !!!";
    }


}
