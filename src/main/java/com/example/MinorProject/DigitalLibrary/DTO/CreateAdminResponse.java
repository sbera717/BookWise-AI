package com.example.MinorProject.DigitalLibrary.DTO;

import com.example.MinorProject.DigitalLibrary.Entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminResponse {
    private  int id;
    private String name;
    private String contact;
    private Date CreatedOn;

    public CreateAdminResponse(Admin admin){
        this.id = admin.getId();
        this.name = admin.getName();
        this.contact = admin.getContact();
        this.CreatedOn = new Date(System.currentTimeMillis());
    }
}
