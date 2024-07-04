package com.example.MinorProject.DigitalLibrary.DTO;

import com.example.MinorProject.DigitalLibrary.Entity.Student;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateStudentResponse implements Serializable {


    private int id;

    private  String name;

    private String contact;
    private String email;
    private Date createdOn;
    private  Date updatedOn;

    private Date validity;

    private String status;

    public CreateStudentResponse (Student student,String response){ //constructor
        this.id = student.getId();
        this.name = student.getName();
        this.contact = student.getContact();
        this.email = student.getEmail();
        this.createdOn = student.getCreatedOn();
        this.updatedOn = student.getUpdatedOn();
        this.validity = student.getValidity();
        this.status = response;
    }

}
