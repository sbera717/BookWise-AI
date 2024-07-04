package com.example.MinorProject.DigitalLibrary.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;


    private String name;

    private String contact;

    @CreationTimestamp
    private Date CreatedOn;

    @JoinColumn
    @OneToOne
    @JsonIgnoreProperties({"admin"})
    private UserRecord userRecord;
}