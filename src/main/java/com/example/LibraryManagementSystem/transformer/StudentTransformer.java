package com.example.LibraryManagementSystem.transformer;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.model.LibraryCard;
import com.example.LibraryManagementSystem.model.Student;

import java.util.UUID;

public class StudentTransformer {

    public static Student studentRequestToStudent(StudentRequest studentRequest) {
        Student student = Student.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();

        // set libraryCard
        student.setLibraryCard(LibraryCardTransformer.libraryCardFromStudent(student));

        return student;
    }

    public static StudentResponse studentToStudentResponse(Student student) {
        return StudentResponse.builder()
                .name(student.getName())
                .email(student.getEmail())
                .message("You have been Registered")
                .cardIsIssuedNo(student.getLibraryCard().getCardNo())
                .build();
    }
}
