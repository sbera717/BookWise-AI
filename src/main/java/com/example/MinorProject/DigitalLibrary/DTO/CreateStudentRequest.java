package com.example.MinorProject.DigitalLibrary.DTO;

import com.example.MinorProject.DigitalLibrary.Entity.Student;
import com.example.MinorProject.DigitalLibrary.Entity.UserRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@Valid
public class CreateStudentRequest {

    @NotBlank
    private  String name;

    @NotBlank
    private String contact;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    public Student to(){
        return Student.builder()
                .name(this.name)
                .contact(this.contact)
                .email(this.email)
                .userRecord(
                        UserRecord.builder()
                                .username(this.username)
                                .password(this.password)
                                .build()
                )
                .validity(new Date(System.currentTimeMillis() + 31536000000L)) // calling the new date for updating everytime
                .build();
    }
}

