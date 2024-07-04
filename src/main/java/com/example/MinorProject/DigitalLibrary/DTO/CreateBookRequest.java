package com.example.MinorProject.DigitalLibrary.DTO;

import com.example.MinorProject.DigitalLibrary.Entity.Author;
import com.example.MinorProject.DigitalLibrary.Entity.Book;
import com.example.MinorProject.DigitalLibrary.Entity.Genre;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@Valid
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    @NotBlank // check for not null and empty string
    private  String name;
    private Genre genre;
    @NotNull
    private  int pages;
    @NotBlank
    private String authorEmail;
    private String authorName;

    private  String authorCountry;

    public Book to(){
        return Book.builder()
                .name(this.name)
                .genre(this.genre)
                .pages(this.pages)
                .my_author(
                        Author.builder()
                                .authorEmail(this.authorEmail)
                                .authorName(this.authorName)
                                .authorCountry(this.authorCountry)
                                .build()
                ) // building author from here
                .build();
    }


}

