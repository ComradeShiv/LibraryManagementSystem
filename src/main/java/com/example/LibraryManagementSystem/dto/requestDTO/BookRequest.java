package com.example.LibraryManagementSystem.dto.requestDTO;

import com.example.LibraryManagementSystem.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookRequest {

    String title;
    int noOfPages;
    Genre genre;
    double cost;
    String authorName;
}
