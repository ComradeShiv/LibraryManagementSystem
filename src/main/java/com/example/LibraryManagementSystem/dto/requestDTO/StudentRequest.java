package com.example.LibraryManagementSystem.dto.requestDTO;

import com.example.LibraryManagementSystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequest {

    String name;

    int age;

    String email;

    Gender gender;
}
