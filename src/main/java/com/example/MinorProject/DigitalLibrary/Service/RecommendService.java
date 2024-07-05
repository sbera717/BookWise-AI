package com.example.MinorProject.DigitalLibrary.Service;

import com.example.MinorProject.DigitalLibrary.DTO.SetValueForPayload;
import com.example.MinorProject.DigitalLibrary.Entity.Genre;
import com.example.MinorProject.DigitalLibrary.Repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RecommendService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    SimpleMailMessage simpleMailMessage;

    @Autowired
    JavaMailSender javaMailSender;

    public void RecommendSystem(String book, Genre genre, String email,String authorName) {

        List<String> allBooks = bookRepository.getBooks(genre);
        Map<String, String> bookAuthorMap = new HashMap<>();
        for (String bookAuthorString : allBooks) {
            String[] parts = bookAuthorString.split(",");
            if (parts.length == 2) {
                String book1 = parts[0].trim();
                String author = parts[1].trim();
                bookAuthorMap.put(book1, author);
            }
        }
        SetValueForPayload setValue = new SetValueForPayload();
        setValue.setBook(book);
        setValue.setAuthor(authorName);
        setValue.setAllBooks(bookAuthorMap);
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject event = objectMapper.convertValue(setValue, JSONObject.class);
        String payload;
        try {
            payload = objectMapper.writeValueAsString(event);
        } catch (Exception e) {
            throw new RuntimeException("Not able to convert", e);
        }
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        HttpEntity httpEntity = new HttpEntity<>(payload, httpHeaders);
        String url = "http://:8000/allbooks";

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                Map.class
        );
        Map<String, String> responseBody = response.getBody();
        List<Map.Entry<String, String>> entries = new ArrayList<>(responseBody.entrySet());

        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Map.Entry<String, String> entry : entries) {
            if (!Objects.equals(entry.getKey(), book)) {
                keys.add(entry.getKey());
                values.add(entry.getValue());
            }
        }

        String book1 = keys.get(0) + " by " + values.get(0);
        String book2 = keys.get(1) + " by " + values.get(1);
        String book3 = keys.get(2) + " by " + values.get(2);

        String body = "Hi!\n\n" +
                "Based on your return, here are a few books you might like..\n\n" +
                book1 + "\n" +
                book2 + "\n" +
                book3 + "\n\n" +
                "Thank you for exploring the world of knowledge with us. Happy reading!";


        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Books Recommendation");
        simpleMailMessage.setFrom("koulik.saha14@gmail.com");
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
    }
}
