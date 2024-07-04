package com.example.MinorProject.DigitalLibrary.DTO;

import com.example.MinorProject.DigitalLibrary.Entity.Book;
import com.example.MinorProject.DigitalLibrary.Entity.Genre;
import com.example.MinorProject.DigitalLibrary.Entity.Student;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookResponse {
    private int id;

    private  String name;

    private Genre genre;

    private  int pages;

    private String authorName;

    private  String authorCountry;

    private  String  authorEmail;

    private Student student;

    private Date createdOn;

    private Date updatedOn;

    private String message;

    public static CreateBookResponse from(Book b){ //method
        return  CreateBookResponse.builder()
                .id(b.getId())
                .name(b.getName())
                .genre(b.getGenre())
                .pages(b.getPages())
                .authorName(b.getMy_author().getAuthorName())
                .authorEmail(b.getMy_author().getAuthorEmail())
                .authorCountry(b.getMy_author().getAuthorCountry())
                .student(b.getStudent())
                .createdOn(b.getCreatedOn())
                .updatedOn(b.getUpdatedOn())
                .message("Book is deleted successfully")
                .build();
    }

}