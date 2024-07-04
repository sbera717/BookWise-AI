package com.example.MinorProject.DigitalLibrary.DTO;


import com.example.MinorProject.DigitalLibrary.Entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookAll {
    private String name;
    private int pages;
    private Genre genre;

    private String authorName;
}

