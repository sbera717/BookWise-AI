package com.example.MinorProject.DigitalLibrary.DTO;

import com.example.MinorProject.DigitalLibrary.Entity.Student;
import com.example.MinorProject.DigitalLibrary.Entity.UserRecord;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Valid
public class UpdateStudentRequest {

    private  String name;

    private String contact;

    private  String email;

    private String password;

    public Student update(){
       return Student.builder()
                .name(this.name)
                .contact(this.contact)
                .email(this.email)
               .userRecord(
                       UserRecord.builder()
                               .password(this.password)
                               .build()
               )
                .build();
    }



}