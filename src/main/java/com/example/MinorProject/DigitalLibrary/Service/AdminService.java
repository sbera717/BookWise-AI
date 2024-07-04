package com.example.MinorProject.DigitalLibrary.Service;

import com.example.MinorProject.DigitalLibrary.DTO.CreateAdminRequest;
import com.example.MinorProject.DigitalLibrary.DTO.CreateAdminResponse;
import com.example.MinorProject.DigitalLibrary.Entity.Admin;
import com.example.MinorProject.DigitalLibrary.Entity.UserRecord;
import com.example.MinorProject.DigitalLibrary.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Value("${admin.authorities}")
    String authorities;

    public CreateAdminResponse createAAdmin(CreateAdminRequest createAdminRequest){
        Admin admin = createAdminRequest.to();
        UserRecord userRecord = admin.getUserRecord();
        userRecord.setPassword(passwordEncoder.encode(userRecord.getPassword()));
        userRecord.setAuthorities(authorities);
        userRecord = userService.save(userRecord);
        admin.setUserRecord(userRecord);
        CreateAdminResponse createAdminResponse = new CreateAdminResponse(admin);
        adminRepository.save(admin);
        return createAdminResponse;


    }

}
