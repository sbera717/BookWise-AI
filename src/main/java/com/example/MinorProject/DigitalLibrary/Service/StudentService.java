package com.example.MinorProject.DigitalLibrary.Service;

import com.example.MinorProject.DigitalLibrary.DTO.CreateStudentRequest;
import com.example.MinorProject.DigitalLibrary.DTO.CreateStudentResponse;
import com.example.MinorProject.DigitalLibrary.DTO.UpdateStudentRequest;
import com.example.MinorProject.DigitalLibrary.Entity.Student;
import com.example.MinorProject.DigitalLibrary.Entity.UserRecord;
import com.example.MinorProject.DigitalLibrary.Repository.StudentCacheRepository;
import com.example.MinorProject.DigitalLibrary.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    StudentCacheRepository studentCacheRepository;

    @Value("${student.authorities}")
    String authorities;

    public CreateStudentResponse createAStudent(CreateStudentRequest createStudentRequest) {
        Student student = createStudentRequest.to();
        UserRecord userRecord = student.getUserRecord();
        userRecord.setPassword(passwordEncoder.encode(userRecord.getPassword()));
        userRecord.setAuthorities(authorities);
        userRecord = userService.save(userRecord);
        student.setUserRecord(userRecord);
        CreateStudentResponse createStudentResponse = new CreateStudentResponse(student,"Account Created Successfully");
        studentRepository.save(student);
        createStudentResponse.setCreatedOn(new Date(System.currentTimeMillis()));
        createStudentResponse.setUpdatedOn(new Date(System.currentTimeMillis()));
        return createStudentResponse;
    }

    public Student getAStudent(int id)
    {
        return studentRepository.findById(id).orElse(null);
    }

    public CreateStudentResponse getAStudentUsingCache(int id){

        CreateStudentResponse student = studentCacheRepository.get(id);
        if(student == null){
            Student studentExisting = studentRepository.findById(id).orElse(null);
            if(studentExisting != null){
                student = new CreateStudentResponse(studentExisting,"Success");
                studentCacheRepository.set(student);
            }else{
                throw  new NoSuchElementException("No student found with ID:"  + id);
            }
        }
        return student;

    }

    //To Do
    public CreateStudentResponse updateAStudent(int id , UpdateStudentRequest updateStudentRequest){
        Student student = studentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        UserRecord userRecord = student.getUserRecord();
        if(updateStudentRequest.getName() != null){
            student.setName(updateStudentRequest.getName());
        }
        if(updateStudentRequest.getEmail() != null){
            student.setEmail(updateStudentRequest.getEmail());
        }
        if(updateStudentRequest.getContact() != null){
            student.setContact(updateStudentRequest.getContact());
        }
        if(updateStudentRequest.getPassword() != null){
            userRecord.setPassword(passwordEncoder.encode(updateStudentRequest.getPassword()));
            student.setUserRecord(userRecord);
        }
        CreateStudentResponse createStudentResponse = new CreateStudentResponse(student,"Account updated Successfully");
        studentRepository.save(student);
        return  createStudentResponse;
    }


    public CreateStudentResponse deleteAStudent(int id) {
        Student student = getAStudent(id);
        CreateStudentResponse createStudentResponse = new CreateStudentResponse(student,"Account Deleted Successfully");
        studentRepository.deleteById(id);
        return createStudentResponse;
    }
}
