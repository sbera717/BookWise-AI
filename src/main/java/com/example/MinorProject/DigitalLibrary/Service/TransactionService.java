package com.example.MinorProject.DigitalLibrary.Service;

import com.example.MinorProject.DigitalLibrary.DTO.CreateTxnIssue;
import com.example.MinorProject.DigitalLibrary.DTO.CreateTxnReturn;
import com.example.MinorProject.DigitalLibrary.DTO.SearchBookRequest;
import com.example.MinorProject.DigitalLibrary.Entity.*;
import com.example.MinorProject.DigitalLibrary.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    BookService bookService;

    @Autowired
    StudentService studentService;

    @Value("${student.issue.number_of_days}")
    private int maxBookAllowedForIssuance;

    @Value("${student.issue.max_books}")
    private int noOfDaysAllowedAfterIssuance;

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private RecommendService recommendService;



    public CreateTxnIssue issueTxn(String bookName, int studentId) throws Exception {
        List<Book> bookList;

        try {
            bookList = bookService.bookSearch(
                    SearchBookRequest.builder()
                            .searchKey("name")
                            .searchValue(bookName)
                            .operator("=")
                            .available(true)
                            .build()

            );
        }
        catch (Exception e){
            throw new Exception("Book Not Found");
        }
        Student student = studentService.getAStudent(studentId);

        //validation
        if(student.getBookList()!= null && student.getBookList().size() >= maxBookAllowedForIssuance){
            throw  new Exception("Book Limit Reached");
        }

        if(bookList.isEmpty()){
            throw new Exception("Not able to find any book in the library");
        }
        Book book = bookList.get(0);
        TransactionDetails transactionDetails = TransactionDetails.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .student(student)
                .book(book)
                .transactionStatus(TransactionStatus.PENDING)
                .build();

        transactionDetails = transactionRepository.save(transactionDetails);
        String externalTxnId;
        try {
            bookService.assignBookToStudent(book, student);

            transactionDetails.setTransactionStatus(TransactionStatus.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            transactionDetails.setTransactionStatus(TransactionStatus.FAILED);
        }finally {
            externalTxnId = transactionRepository.save(transactionDetails).getExternalTxnId();
        }
        return new CreateTxnIssue(book.getId(),book.getName(),externalTxnId);
    }

    public CreateTxnReturn returnTxn(int Id,int bookId) throws Exception {
        Book book;
        try{
            book = bookService.bookSearch(
                    SearchBookRequest.builder()
                            .searchKey("id")
                            .searchValue(String.valueOf(bookId))
                            .build()
            ).get(0);
        } catch (Exception e) {
            throw new Exception("not able to fetch book details");
        }
        Student student = studentService.getAStudent(Id);


        if(book.getStudent() == null || book.getStudent().getId() != Id){
            throw new Exception("Book is not assigned to this student");
        }
        TransactionDetails transactionDetails = TransactionDetails.builder()
                .externalTxnId(UUID.randomUUID().toString())
                .transactionType(TransactionType.RETURN)
                .student(student)
                .book(book)
                .transactionStatus(TransactionStatus.PENDING)
                .build();

        transactionDetails = transactionRepository.save(transactionDetails);

        TransactionDetails issueTxnDb;
        double fine = 0;
        try{
            issueTxnDb  = transactionRepository.findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByTransactionTimeDesc(student,book,TransactionType.ISSUE,TransactionStatus.SUCCESS);
            long issueTxnInMillis = issueTxnDb.getTransactionTime().getTime();
            long currentTimeMillis = System.currentTimeMillis();
            long timeDifferenceInMillis = currentTimeMillis - issueTxnInMillis;
            long timeDifferenceInDays = TimeUnit.DAYS.convert(timeDifferenceInMillis,TimeUnit.MILLISECONDS);
            if(timeDifferenceInDays > noOfDaysAllowedAfterIssuance){
                fine = (timeDifferenceInDays - noOfDaysAllowedAfterIssuance) * 1.0;
            }
        }catch (Exception e){
            fine = 0;
        }

        String externalTxnId;
        try{
            bookService.unassignBookToStudent(book);
            transactionDetails.setTransactionStatus(TransactionStatus.SUCCESS);
            boolean recommendationSuccess = false;
            int retryCount = 0;
            int maxRetries = 50;
            while(!recommendationSuccess && retryCount <= maxRetries){
                try{
                    recommendService.RecommendSystem(book.getName(),book.getGenre(),student.getEmail(),book.getMy_author().getAuthorName());
                    recommendationSuccess = true;
                }
                catch (Exception e){
                    retryCount++;
                    e.printStackTrace();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            transactionDetails.setTransactionStatus(TransactionStatus.FAILED);
        }finally {
            transactionDetails.setFine(fine);
            externalTxnId = transactionRepository.save(transactionDetails).getExternalTxnId();
        }
        return new CreateTxnReturn(book.getName(),externalTxnId);

    }





}
