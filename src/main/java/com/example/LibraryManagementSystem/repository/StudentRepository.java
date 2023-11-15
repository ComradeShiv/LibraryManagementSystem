package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByGender(Gender gender);

    String findByEmail(String email);

    String findByGenderAndEmail(Gender gender, String email);
}
