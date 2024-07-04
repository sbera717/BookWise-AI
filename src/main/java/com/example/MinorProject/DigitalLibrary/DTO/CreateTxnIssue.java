package com.example.MinorProject.DigitalLibrary.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CreateTxnIssue {

    private int bookId;
    private String issuedBook;
    private String transactionId;
    private Date dueDate;

    public CreateTxnIssue(int bookId,String bookName,String Id){
        this.bookId = bookId;
        issuedBook = bookName;
        transactionId = Id;
        dueDate = new Date(System.currentTimeMillis() + 1296000000L);
    }
}
