package com.example.LibraryManagementSystem.transformer;

import com.example.LibraryManagementSystem.dto.requestDTO.AuthorRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.model.Author;

public class AuthorTransformer {

    public static AuthorResponse authorToAuthorResponse(Author author) {
        return AuthorResponse.builder()
                .name(author.getName())
                .age(author.getAge())
                .email(author.getEmailId())
                .build();
    }

    public static Author authorRequestToAuthor(AuthorRequest authorRequest) {
        return Author.builder()
                .name(authorRequest.getName())
                .age(authorRequest.getAge())
                .emailId(authorRequest.getEmail())
                .build();
    }
}
