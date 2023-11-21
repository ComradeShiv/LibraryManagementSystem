package com.example.LibraryManagementSystem.dto.responseDTO;

import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ReturnBookResponse {

    String transactionNumber; // UUID

    Date transactionTime;

    TransactionStatus transactionStatus;

    String bookTitle;

    String authorName;

    String studentName;

    String libraryCardNumber;
}
