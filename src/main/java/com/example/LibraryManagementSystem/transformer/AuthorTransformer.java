package com.example.LibraryManagementSystem.transformer;

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
}
