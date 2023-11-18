package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;
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
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestParam("id") int regNo) {
        StudentResponse studentResponse = studentService.deleteStudent(regNo);
        if(studentResponse == null)
            return new ResponseEntity("Invalid ID !!!", HttpStatus.NOT_FOUND);
        return new ResponseEntity(studentResponse, HttpStatus.GONE);
    }

//    update the age of s student ---> regNo, age
    @PutMapping("/updateAge")
    public String updateAge(@RequestParam("newAge") int newAge, @RequestParam("id") int regNo) {
        try{
            int updatedAge = studentService.updateAge(newAge, regNo);
            return "Age has been updated to: " + updatedAge;
        } catch (StudentNotFoundException e) {
            return e.getMessage();
        }
    }

//    get all the students int DB ---> findAll()
    @GetMapping("/getAllStudent")
    public ResponseEntity findAllStudent() {
        List<StudentResponse> responses = studentService.findAllStudent();
        if(responses.isEmpty())
            return new ResponseEntity("No student details found", HttpStatus.NOT_FOUND);
        return new ResponseEntity(responses, HttpStatus.FOUND);
    }

//    get list of all male students ---> iterate over findAll()
    @GetMapping("/getMale")
    public ResponseEntity findAllMales() {
        List<String> maleList = studentService.findAllMales();
        return new ResponseEntity(maleList, HttpStatus.FOUND);
    }
}
