package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

     List<Book> findByGenre(Genre genre);

     @Query(value = "select * from book where genre = :genre and cost > :cost", nativeQuery = true)
     List<Book> getAllBooksWithGenreAndCostGreaterThan(String genre, double cost);

     Book deleteById(int id);
}
