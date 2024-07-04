package com.example.MinorProject.DigitalLibrary.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Valid

public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    private  String name;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private  int pages;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"bookList"})
    private Author my_author;

    @JsonIgnoreProperties({"bookList"})
    @ManyToOne
    @JoinColumn
    private Student student;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    private Date dueDate;

    @JsonIgnoreProperties({"book"} )
    @OneToMany(mappedBy = "book" , fetch = FetchType.EAGER)
    private List<TransactionDetails> transactionDetailsList;
}

