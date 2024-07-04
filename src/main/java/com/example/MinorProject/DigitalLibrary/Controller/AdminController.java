package com.example.MinorProject.DigitalLibrary.Controller;

import com.example.MinorProject.DigitalLibrary.DTO.CreateAdminRequest;
import com.example.MinorProject.DigitalLibrary.DTO.CreateAdminResponse;
import com.example.MinorProject.DigitalLibrary.Entity.Admin;
import com.example.MinorProject.DigitalLibrary.Service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;



    @PostMapping("/createAdmin")
    public CreateAdminResponse createAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest){
        return adminService.createAAdmin(createAdminRequest);
    }



}
