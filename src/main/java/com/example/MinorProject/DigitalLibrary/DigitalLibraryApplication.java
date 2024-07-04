package com.example.MinorProject.DigitalLibrary;

import com.example.MinorProject.DigitalLibrary.DTO.CreateAdminRequest;
import com.example.MinorProject.DigitalLibrary.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DigitalLibraryApplication implements CommandLineRunner {

	public static void main(String[] args)  {
		SpringApplication.run(DigitalLibraryApplication.class, args);

	}

	@Autowired
	AdminService adminService;
	@Override
	public void run(String... args) throws Exception {

//		adminService.createAAdmin(
//				CreateAdminRequest.builder()
//						.name("Subrat")
//						.contact("9898989898")
//						.username("subrat@gmail.com")
//						.password("subrat@123")
//						.build()
//		);
	}

}