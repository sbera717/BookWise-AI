package com.example.MinorProject.DigitalLibrary.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Valid
@Entity
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String authorName;

    private  String authorCountry;

    @Column(unique = true,nullable = false)
    private  String authorEmail;

    @CreationTimestamp
    private Date createdOn;

    @JsonIgnoreProperties({"my_author"})
    @OneToMany(mappedBy = "my_author")
    private List<Book> bookList;
}