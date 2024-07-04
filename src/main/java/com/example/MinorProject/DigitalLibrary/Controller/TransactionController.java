package com.example.MinorProject.DigitalLibrary.Controller;

import com.example.MinorProject.DigitalLibrary.DTO.CreateTxnIssue;
import com.example.MinorProject.DigitalLibrary.DTO.CreateTxnReturn;
import com.example.MinorProject.DigitalLibrary.Entity.UserRecord;
import com.example.MinorProject.DigitalLibrary.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction/issue")
    public CreateTxnIssue issueTxn(@RequestParam("name") String name ) throws Exception {
        UserRecord userRecord = (UserRecord) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int studentId = userRecord.getStudent().getId();
        return transactionService.issueTxn(name, studentId);
    }
    @PostMapping("/transaction/return")
    public CreateTxnReturn returnTxn(@RequestParam("bookId") int bookId) throws Exception {
        UserRecord userRecord = (UserRecord) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int Id = userRecord.getStudent().getId();
        return transactionService.returnTxn(Id, bookId);
    }
}
