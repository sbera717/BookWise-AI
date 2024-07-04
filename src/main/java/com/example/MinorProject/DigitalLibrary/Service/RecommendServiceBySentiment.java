package com.example.MinorProject.DigitalLibrary.Service;


import com.example.MinorProject.DigitalLibrary.DTO.BookAll;
import com.example.MinorProject.DigitalLibrary.DTO.CreateSentimentRequest;
import com.example.MinorProject.DigitalLibrary.DTO.SetValueForSentimentAnalysis;
import com.example.MinorProject.DigitalLibrary.Repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecommendServiceBySentiment {

    @Autowired
    private BookRepository bookRepository;

    public String Sentiment(CreateSentimentRequest createSentimentRequest){
        List<String> allBooks = bookRepository.getAllBooksInside();
        Map<String, String> bookAuthorMap = new HashMap<>();
        for (String bookAuthorString : allBooks) {
            String[] parts = bookAuthorString.split(",");
            if (parts.length == 2) {
                String book1 = parts[0].trim();
                String author = parts[1].trim();
                bookAuthorMap.put(book1, author);
            }
        }
        SetValueForSentimentAnalysis analysis = new SetValueForSentimentAnalysis();
        analysis.setUserText(createSentimentRequest.getUserInput());
        analysis.setAllBooks(bookAuthorMap);
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject event = objectMapper.convertValue(analysis, JSONObject.class);
        String payload;
        try{
            payload = objectMapper.writeValueAsString(event);
        }catch (Exception e){
            throw new RuntimeException("Not able to convert",e);
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        HttpEntity httpEntity = new HttpEntity<>(payload,httpHeaders);
        String url = "http://20.64.235.142:8000/user";
        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                Map.class
        );
        Map<String, String> responseBody = response.getBody();

        String recommendedBook = responseBody.get("Recommended Book");

        if(recommendedBook != null){
            return "This Book may help you: " + recommendedBook;
        }
        else{
            return "Please try again, and could you please provide a detailed explanation of your feelings as an AI model? This will help me suggest a better book for you.";
        }



    }

}
