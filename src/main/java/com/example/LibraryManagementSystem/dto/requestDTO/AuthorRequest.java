package com.example.LibraryManagementSystem.dto.requestDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthorRequest {

    String name;
    int age;
    String email;
}
