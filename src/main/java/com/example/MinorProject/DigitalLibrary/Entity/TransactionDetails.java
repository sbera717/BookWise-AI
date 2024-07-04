package com.example.MinorProject.DigitalLibrary.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.Valid;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Valid

public class TransactionDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double fine;

    private String externalTxnId;

    @CreationTimestamp
    private Date transactionTime;
    @UpdateTimestamp
    private Date updatedOn;

    @Enumerated(value = EnumType.STRING)
    private  TransactionStatus transactionStatus;

    @Enumerated(value  = EnumType.STRING)
    private  TransactionType transactionType;

    @JsonIgnoreProperties({"transactionList"})
    @ManyToOne
    @JoinColumn
    private Book book;

    @JsonIgnoreProperties({"transactionList"})
    @ManyToOne
    @JoinColumn
    private Student student;
}