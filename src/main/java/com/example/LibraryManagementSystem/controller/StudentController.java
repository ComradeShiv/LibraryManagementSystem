package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.service.StudentService;
import com.example.LibraryManagementSystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest) {
        StudentResponse response = studentService.addStudent(studentRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo) {
        Student response = studentService.getStudent(regNo);
        if(response != null)
            return new ResponseEntity(response, HttpStatus.FOUND);

        return new ResponseEntity("Invalid ID!!", HttpStatus.BAD_REQUEST);
    }


//    Delete a student

//    update the age of s student ---> regNo, age

//    get all the students int DB ---> findAll()

//    get list of all male students ---> iterate over findAll()
    @GetMapping("/getMale")
    public ResponseEntity findAllMales() {
        List<String> maleList = studentService.findAllMales();
        return new ResponseEntity(maleList, HttpStatus.FOUND);
    }
}
