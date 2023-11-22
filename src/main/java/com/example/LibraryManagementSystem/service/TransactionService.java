package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.IssuedBookResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.ReturnBookResponse;
import com.example.LibraryManagementSystem.exception.BookNotAvailableException;
import com.example.LibraryManagementSystem.exception.BookNotFoundException;
import com.example.LibraryManagementSystem.exception.BookNotIssuedException;
import com.example.LibraryManagementSystem.exception.StudentNotFoundException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.model.Transaction;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.repository.TransactionRepository;
import com.example.LibraryManagementSystem.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public IssuedBookResponse issuedBook(int studentId, int bookId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(!optionalStudent.isPresent()) { // check student id validity
            throw new StudentNotFoundException("Invalid Student ID !!");
        }

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(!optionalBook.isPresent()) { // check book id validity
            throw new BookNotFoundException("Invalid Book ID !!");
        }

        Book book = optionalBook.get();
        if(book.isIssued()) { // check whether book is issued or not
            throw new BookNotAvailableException("Book Already Issued");
        }

        Student student = optionalStudent.get();

        // create new transaction
        Transaction transaction = Transaction.builder()
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.SUCCESS)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();

        // save transaction --> so it doesn't get saved twice
        Transaction savedTransaction = transactionRepository.save(transaction);

        // update book
        book.setIssued(true);
        book.getTransactionList().add(savedTransaction);

        // update libraryCard
        student.getLibraryCard().getTransactionList().add(savedTransaction);

        // save changes
        Book savedBook = bookRepository.save(book);
        Student savedStudent = studentRepository.save(student);

        //  send an email
        String text = "Hi! " + student.getName() + " The below book has been issued to you\n" +
                book.getTitle() + " \nThis is the transaction number: "+savedTransaction.getTransactionNumber();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("sukanshitalreja2211@gmail.com");
        simpleMailMessage.setTo(student.getEmail());
        simpleMailMessage.setSubject("Congrats!! Book Issued");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        // create issued book response
        IssuedBookResponse issuedBookResponse = BookTransformer.prepareIssuedBookResponse(savedTransaction, book, student);
        return issuedBookResponse;
    }

    public ReturnBookResponse returnBook(int studentId, int bookId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(!optionalStudent.isPresent()) { // check student id validity
            throw new StudentNotFoundException("Invalid Student ID !!");
        }

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(!optionalBook.isPresent()) { // check book id validity
            throw new BookNotFoundException("Invalid Book ID !!");
        }

        Book book = optionalBook.get();
        if(!book.isIssued()) { // check whether book is issued or not
            throw new BookNotIssuedException("Book Not Issued Yet");
        }

        Student student = optionalStudent.get();

        // create transaction
        Transaction transaction = Transaction.builder()
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.SUCCESS)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();

        // save transaction --> so it doesn't get saved twice
        Transaction savedTransaction = transactionRepository.save(transaction);

        // update book
        book.setIssued(false);
        book.getTransactionList().add(savedTransaction);

        // update libraryCard
        student.getLibraryCard().getTransactionList().add(savedTransaction);

        //save changes
        bookRepository.save(book);
        studentRepository.save(student);

        // create return book response
        ReturnBookResponse returnBookResponse = BookTransformer.prepareReturnBookResponse(savedTransaction, book, student);
        return returnBookResponse;
    }
}
