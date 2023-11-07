package com.example.LibraryManagementSystem.model;


import com.example.LibraryManagementSystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Student_info")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int regNo;

    @Column(name = "stuName")
    String name;

    int age;

    @Column(unique = true, nullable = false)
    String email;

    int marks;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    LibraryCard libraryCard;
}
