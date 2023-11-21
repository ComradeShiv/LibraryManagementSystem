package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;
import com.example.LibraryManagementSystem.model.LibraryCard;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {

        Student student = StudentTransformer.studentRequestToStudent(studentRequest); // create student & set libraryCard as well
        Student savedStudent = studentRepository.save(student);  // save both student & library class in DB

        // save model to response DTOs
        StudentResponse studentResponse = StudentTransformer.studentToStudentResponse(savedStudent);
        return studentResponse;
    }

    public Student getStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent())
            return studentOptional.get();
        return null;
    }

    public List<String> findAllMales() {
        List<String> names = new ArrayList<>();
        List<Student> students = studentRepository.findByGender(Gender.MALE);
        for(Student student: students)
            names.add(student.getName());

        return names;
    }

    public StudentResponse deleteStudent(int regNo) {
        Optional<Student> optionalStudent = studentRepository.findById(regNo);
        if(!optionalStudent.isPresent()) {
            return null;
        }

        // delete student details
        Student deletedStudent = studentRepository.deleteById(regNo);

        // add details to student DTO
        StudentResponse studentResponse = StudentTransformer.studentToStudentResponse(deletedStudent);
        return studentResponse;
    }

    public int updateAge(int newAge, int regNo) {
        Optional<Student> optionalStudent = studentRepository.findById(regNo);
        if(!optionalStudent.isPresent()) {
            throw new StudentNotFoundException("Invalid student ID !!!");
        }

        optionalStudent.get().setAge(newAge);
        Student student = studentRepository.save(optionalStudent.get());
        return optionalStudent.get().getAge();
    }

    public List<StudentResponse> findAllStudent() {
        List<Student> students = studentRepository.findAll();

        List<StudentResponse> studentResponses = new ArrayList<>();
        for(Student student: students) {
            StudentResponse studentResponse = StudentTransformer.studentToStudentResponse(student);
            studentResponses.add(studentResponse);
        }

        return studentResponses;
    }
}
