package com.example.MinorProject.DigitalLibrary.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetValueForPayload {
    private String book;
    private String author;
    private Map<String,String> allBooks;

}
