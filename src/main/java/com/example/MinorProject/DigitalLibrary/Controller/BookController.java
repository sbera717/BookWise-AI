package com.example.MinorProject.DigitalLibrary.Controller;

import com.example.MinorProject.DigitalLibrary.DTO.*;
import com.example.MinorProject.DigitalLibrary.Entity.Book;
import com.example.MinorProject.DigitalLibrary.Service.BookService;
import com.example.MinorProject.DigitalLibrary.Service.RecommendServiceBySentiment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private RecommendServiceBySentiment serviceBySentiment;


    @PostMapping("/book/createBook")
    public Book createBook(@RequestBody @Valid CreateBookRequest createBookRequest){
        return bookService.createABook(createBookRequest);

    }

    @GetMapping("/book/all")
    public List<BookAll> getAllBook(){
        return  bookService.getAllBookFromDB();
    }


    @DeleteMapping("/book/{id}")
    // to do : global exception handler
    public CreateBookResponse deleteBook(@PathVariable ("id") int id){
        return bookService.deleteABook(id);
    }


    @GetMapping("/book/search")
    public  List<BookAll> searchByDifferentValue(@RequestParam @NotBlank String searchKey,
                                                 @RequestParam @NotBlank String searchValue,
                                                 @RequestParam @NotBlank String operator) throws Exception {
        SearchBookRequest searchBookRequest = new SearchBookRequest(searchKey,searchValue,operator,true);
        return bookService.ControllerbookSearch(searchBookRequest);
    }

    @GetMapping("/book/suggestBook")
    public String suggestBySentiment(@RequestParam @NotBlank String userInput){
        CreateSentimentRequest createSentimentRequest = new CreateSentimentRequest(userInput);
        return serviceBySentiment.Sentiment(createSentimentRequest);
    }


}
