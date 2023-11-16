package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.model.LibraryCard;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.repository.StudentRepository;
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

        //converting request DTOs to model class
        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setAge(studentRequest.getAge());
        student.setGender(studentRequest.getGender());
        student.setEmail(studentRequest.getEmail());

        // give a library card
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard); // set library card to student

        Student savedStudent = studentRepository.save(student);  // save both student & library class in DB

        // save model to response DTOs
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(savedStudent.getName());
        studentResponse.setEmail(savedStudent.getEmail());
        studentResponse.setCardIsIssuedNo(savedStudent.getLibraryCard().getCardNo());
        studentResponse.setMessage("You have been registered");
        return studentResponse;
    }

    public Student getStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent())
            return studentOptional.get();
        return null;
    }

//    public List<String> findAllMales() {
//        List<String> list = new ArrayList<>();
//
//        for(Student s: studentRepository.findAll())
//            if(s.getGender().equals(Gender.MALE))
//                list.add(s.getName());
//
//        return list;
//    }

    public List<String> findAllMales() {
        List<String> names = new ArrayList<>();
        List<Student> students = studentRepository.findByGender(Gender.MALE);
        for(Student student: students)
            names.add(student.getName());

        return names;
    }
}
