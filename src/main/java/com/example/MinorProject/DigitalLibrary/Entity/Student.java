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
@Valid
@Entity
@Builder

public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private  String name;

    private String contact;

    @Column(unique = true,nullable = false)
    private  String email;

    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private  Date updatedOn;

    private Date validity;


    @JoinColumn
    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"student"})
    private UserRecord userRecord;


    @JsonIgnoreProperties({"student"})
    @OneToMany(mappedBy = "student")
    private List<Book> bookList;

    @JsonIgnoreProperties({"student"})
    @OneToMany(mappedBy = "student")
    private List<TransactionDetails> transactionList;

}
