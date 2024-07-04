package com.example.MinorProject.DigitalLibrary.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetValueForSentimentAnalysis {
    private String userText;
    private Map<String,String> allBooks;
}
