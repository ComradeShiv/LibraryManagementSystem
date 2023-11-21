package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

     Author findByName(String authorName);

    Optional<Author> findByEmailId(String email);

    @Query(value = "SELECT author.name, COUNT(book.id) AS book_count FROM author JOIN book ON author.id = book.author_id GROUP BY author.id, author.name HAVING COUNT(book.id) > :x", nativeQuery = true)
    List<String> listOfAuthorsWrittenBooksGreaterThan(int x);

//    List<Student> findByGender(Gender gender);
//
//    Student findByGender(String gender);
//
//    Student findByGenderAndEmail(Gender gender, String email); // if gender written first in function name then give gender input also first

}
