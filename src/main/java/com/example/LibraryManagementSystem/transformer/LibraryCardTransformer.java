package com.example.LibraryManagementSystem.transformer;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.model.LibraryCard;
import com.example.LibraryManagementSystem.model.Student;

import java.util.UUID;

public class LibraryCardTransformer {

    public static LibraryCard libraryCardFromStudent(Student student) {
        return LibraryCard.builder()
                .cardNo(String.valueOf(UUID.randomUUID()))
                .cardStatus(CardStatus.ACTIVE)
                .student(student)
                .build();
    }
}
