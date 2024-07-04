package com.example.MinorProject.DigitalLibrary.Service;

import com.example.MinorProject.DigitalLibrary.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@Service
public class CronService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    SimpleMailMessage simpleMailMessage;

    @Autowired
    JavaMailSender javaMailSender;

    @Scheduled(cron = "0 0 18 * * *")
    public void emailScheduling(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneDayFromNow = now.plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowFormatted = now.format(formatter);
        String oneDayFromNowFormatted = oneDayFromNow.format(formatter);

        LocalDateTime nowFormattedAsDateTime = LocalDateTime.parse(nowFormatted, formatter);
        LocalDateTime oneDayFromNowFormattedAsDateTime = LocalDateTime.parse(oneDayFromNowFormatted, formatter);

        Date nowDate = Timestamp.valueOf(nowFormattedAsDateTime);
        Date oneDayFromNowDate = Timestamp.valueOf(oneDayFromNowFormattedAsDateTime);

        List<String> allEmail = bookRepository.getEmailAddress(nowDate,oneDayFromNowDate);
        System.out.println(allEmail);


        String body = "Hi!\n\n" +
                "Your due date to return the book is tomorrow.\n" +
                "Please return the book before the due date; otherwise, fines will be charged.\n\n" +
                "Thank you for joining us on this enlightening journey. Happy reading!\n";
        for (String s : allEmail) {
            simpleMailMessage.setTo(s);
            simpleMailMessage.setSubject("Due Date Tomorrow to return book");
            simpleMailMessage.setFrom("koulik.saha14@gmail.com");
            simpleMailMessage.setText(body);
            javaMailSender.send(simpleMailMessage);
        }



    }
}
