package com.example.MinorProject.DigitalLibrary.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTxnReturn {
    private String returnedBook;
    private String transactionId;

    public CreateTxnReturn(String bookName,String Id){
        returnedBook = bookName;
        transactionId = Id;
    }



}
