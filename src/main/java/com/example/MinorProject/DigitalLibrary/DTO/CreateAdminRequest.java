package com.example.MinorProject.DigitalLibrary.DTO;


import com.example.MinorProject.DigitalLibrary.Entity.Admin;
import com.example.MinorProject.DigitalLibrary.Entity.UserRecord;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String contact;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Admin to(){
        return Admin.builder()
                .name(this.name)
                .contact(this.contact)
                .userRecord(
                        UserRecord.builder()
                                .username(this.username)
                                .password(this.password)
                                .build()
                )
                .build();
    }




}
