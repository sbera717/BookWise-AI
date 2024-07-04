package com.example.MinorProject.DigitalLibrary.Service;

import com.example.MinorProject.DigitalLibrary.DTO.BookAll;
import com.example.MinorProject.DigitalLibrary.DTO.CreateBookRequest;
import com.example.MinorProject.DigitalLibrary.DTO.CreateBookResponse;
import com.example.MinorProject.DigitalLibrary.DTO.SearchBookRequest;
import com.example.MinorProject.DigitalLibrary.Entity.Author;
import com.example.MinorProject.DigitalLibrary.Entity.Book;
import com.example.MinorProject.DigitalLibrary.Entity.Genre;
import com.example.MinorProject.DigitalLibrary.Entity.Student;
import com.example.MinorProject.DigitalLibrary.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;



    public Book createABook(CreateBookRequest createBookRequest){
        Book book = createBookRequest.to();
        Author author = authorService.createOrGet(book.getMy_author());
        book.setMy_author(author);
        return  bookRepository.save(book);
    }
    public void assignBookToStudent(Book book, Student student){

        Date date = new Date(System.currentTimeMillis() + 1296000000L);
        bookRepository.assignBookToStudent(book.getId(), student,date);
    }
    public void unassignBookToStudent(Book book){
        bookRepository.unassignBookToStudent(book.getId());
    }


    public List<BookAll> getAllBookFromDB() {
        return bookRepository.getAllBooks();
    }

    public CreateBookResponse deleteABook(int id){
        CreateBookResponse responseBook = null;
            Book book = bookRepository.findById(id).orElse(null);
            if(book != null){
                responseBook = CreateBookResponse.from(book);
            }
            bookRepository.deleteById(id);

        return responseBook;
    }
    public List<Book> bookSearch(SearchBookRequest searchBookRequest) throws Exception {

        String value = searchBookRequest.getSearchKey();
        if(value.equals("name")){
            if(searchBookRequest.isAvailable()){
                return bookRepository.findByNameAndStudentIsNull(searchBookRequest.getSearchValue());
            }
            return bookRepository.findByName(searchBookRequest.getSearchValue());
        }
        else if(value.equals("genre")){
            return bookRepository.findByGenre(Genre.valueOf(searchBookRequest.getSearchValue()));
        }
        else if(value.equals("id")){
            Book book = bookRepository.findById(Integer.parseInt(searchBookRequest.getSearchValue())).orElse(null);
            return Collections.singletonList(book);
        }
        else{
            throw new Exception("Invalid Search Key");
        }


    }

    public List<BookAll> ControllerbookSearch(SearchBookRequest searchBookRequest) throws Exception {

        String value = searchBookRequest.getSearchKey();
        if(value.equals("name")){
            return bookRepository.findByName2(searchBookRequest.getSearchValue());
        }
        else if(value.equals("genre")){
            return bookRepository.findByGenre2(Genre.valueOf(searchBookRequest.getSearchValue()));
        }
        else{
            throw new Exception("Invalid Search Key");
        }


    }

}