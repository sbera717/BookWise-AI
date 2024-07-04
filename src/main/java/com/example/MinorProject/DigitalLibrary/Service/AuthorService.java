package com.example.MinorProject.DigitalLibrary.Service;

import com.example.MinorProject.DigitalLibrary.Entity.Author;
import com.example.MinorProject.DigitalLibrary.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author createOrGet(Author author){
        Author authorFromDB = authorRepository.findByAuthorEmail(author.getAuthorEmail());
        if(authorFromDB != null){
            return authorFromDB;
        }
        return authorRepository.save(author);
    }


}
