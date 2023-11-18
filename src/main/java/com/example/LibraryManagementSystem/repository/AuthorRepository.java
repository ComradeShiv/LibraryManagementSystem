package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

     Author findByName(String authorName);

//    List<Student> findByGender(Gender gender);
//
//    Student findByGender(String gender);
//
//    Student findByGenderAndEmail(Gender gender, String email); // if gender written first in function name then give gender input also first

}
