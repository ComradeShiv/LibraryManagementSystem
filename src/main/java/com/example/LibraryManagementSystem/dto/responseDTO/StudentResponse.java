package com.example.LibraryManagementSystem.dto.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class StudentResponse {

    String name;

    String email;

    String message;

    String cardIsIssuedNo;
}
